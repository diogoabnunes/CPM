package org.feup.apm.stockclient;

import org.feup.apm.stockservice.common.IStockQuoteService;
import org.feup.apm.stockservice.common.IStockQuoteServiceResponse;
import org.feup.apm.stockservice.common.Person;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class StockQuoteClientActivity extends AppCompatActivity {
	private IStockQuoteService stockService = null;
	private TextView console;
	private ScrollView scroll;
	private Button bindBtn;
	private Button callBtn;
	private Button unbindBtn;
  private BroadcastReceiver receiver;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);
    console = (TextView) findViewById(R.id.textView);
    scroll = (ScrollView) findViewById(R.id.scView);
    receiver = new BroadcastReceiver() {
      @Override
      public void onReceive(Context context, Intent intent) {
        String message = intent.getStringExtra("message");
        addText(message);
      }
    };

    bindBtn = (Button)findViewById(R.id.bindBtn);
    bindBtn.setOnClickListener(new OnClickListener() {
      public void onClick(View view) {
        if (bindService(new Intent(IStockQuoteService.class.getName()), servConn, Context.BIND_AUTO_CREATE)) {
          bindBtn.setEnabled(false);
          unbindBtn.setEnabled(true);
        }
      }
    });

    callBtn = (Button)findViewById(R.id.callBtn);
    callBtn.setOnClickListener(new OnClickListener() {
      public void onClick(View view) {
          callService();
      }
    });
    callBtn.setEnabled(false);

    unbindBtn = (Button)findViewById(R.id.unbindBtn);
    unbindBtn.setOnClickListener(new OnClickListener() {
      public void onClick(View view) {
        unbindService(servConn);
        bindBtn.setEnabled(true);
        callBtn.setEnabled(false);
        unbindBtn.setEnabled(false);
        addText("call unbind (" + android.os.Process.myTid() + ")");
      }
    });
    unbindBtn.setEnabled(false);
    addText("Activity tid: " + android.os.Process.myTid());
  }

  @Override
  protected void onResume() {
    super.onResume();
    IntentFilter intentFilter = new IntentFilter("android.intent.action.QUOTE_CLIENT");
    registerReceiver(receiver, intentFilter);
  }

  @Override
  protected void onPause() {
    super.onPause();
    unregisterReceiver(receiver);
  }

  private void callService() {
    try {
    	Person person = new Person();
    	person.setAge(47);
    	person.setName("Dave");
      stockService.getQuoteLong("IBM", person, responseListener);
      addText("call service");
    }
    catch (RemoteException re) {
      addText(re.getMessage());
    }
  }

  private void addText(String txt) {
    String newText;

    String old = console.getText().toString();
    if (old.length() == 0)
      newText = txt;
    else
      newText = old + "\n" + txt;
    console.setText(newText);
    scroll.fullScroll(ScrollView.FOCUS_DOWN);
  }

  private ServiceConnection servConn = new ServiceConnection() {
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
      addText("onServiceConnected() called (" + android.os.Process.myTid() + ")");
      stockService = IStockQuoteService.Stub.asInterface(service);
      callBtn.setEnabled(true);
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {
      addText("onServiceDisconnected() called");
    	stockService = null;
      bindBtn.setEnabled(true);
      callBtn.setEnabled(false);
      unbindBtn.setEnabled(false);
    }
  };

  private final IStockQuoteServiceResponse responseListener = new IStockQuoteServiceResponse.Stub() {
    @Override
    public void onQuoteResult(final String result) {
      final int tid = android.os.Process.myTid();
      runOnUiThread(new Runnable() {
        @Override
        public void run() {
          addText("callback called (" + tid + ")");
          addText(result);
          Toast.makeText(StockQuoteClientActivity.this, "Value from service is \"" + result + "\"", Toast.LENGTH_LONG).show();
        }
      });
    }
  };
}
