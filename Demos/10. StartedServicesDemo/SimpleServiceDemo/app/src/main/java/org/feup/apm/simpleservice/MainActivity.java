package org.feup.apm.simpleservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
  static MainActivity myInstance = null;

  private int counter = 1;
  private RadioGroup rg;
  private TextView console;
  private ScrollView scroll;
  private BroadcastReceiver receiver;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    rg = (RadioGroup) findViewById(R.id.radioGroup1);
    rg.setOnCheckedChangeListener(this);
    console = (TextView) findViewById(R.id.textView);
    scroll = (ScrollView) findViewById(R.id.scView);
    MainActivity.myInstance = this;
    receiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        addText(message);
      }
    };
  }

  @Override
  protected void onResume() {
    super.onResume();
    IntentFilter intentFilter = new IntentFilter("android.intent.action.RCV_MESS");
    registerReceiver(receiver, intentFilter);
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterReceiver(receiver);
  }

  public void addText(String text) {
    String old = console.getText().toString();
    console.setText(old + text + "\n");
    scroll.fullScroll(ScrollView.FOCUS_DOWN);
  }

  public void doButtonClick(View view) {
    Intent intent;
    switch(view.getId()) {
      case R.id.startBtn:
        addText("Call service #" + counter + ", tid = " + android.os.Process.myTid());
        if (rg.getCheckedRadioButtonId() == R.id.radio0)
          intent = new Intent(this, BackgroundService.class);
        else
          intent = new Intent("org.feup.apm.intents.ACTION_TESTSERVICE");
        intent.putExtra("counter", counter++);
        startService(intent);
        break;
      case R.id.stopBtn:
        stopService(rg.getCheckedRadioButtonId());
        break;
      case R.id.clearBtn:
        console.setText("");
        scroll.fullScroll(ScrollView.FOCUS_UP);
        break;
    }
  }

  public void onCheckedChanged(RadioGroup rgr, int id) {
    if (id == R.id.radio0)
      stopService(R.id.radio1);
    else
      stopService(R.id.radio0);
  }

  private void stopService(int id) {
    Intent intent;

  	if (id == R.id.radio0)
      intent = new Intent(this, BackgroundService.class);
  	else
  	  intent = new Intent("org.feup.apm.intents.ACTION_TESTSERVICE");
    addText("Call stop service");
  	stopService(intent);
  }

  @Override
  public void onDestroy() {
  	stopService(rg.getCheckedRadioButtonId());
    super.onDestroy();
    MainActivity.myInstance = null;
  }
}
