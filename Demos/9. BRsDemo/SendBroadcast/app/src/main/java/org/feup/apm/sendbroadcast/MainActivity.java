package org.feup.apm.sendbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
  TextView tv;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    tv = findViewById(R.id.text1);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    super.onCreateOptionsMenu(menu);
    MenuInflater inflater = getMenuInflater();      // menu resource reader
    inflater.inflate(R.menu.main_menu, menu);       // read the menu resource
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    appendMenuItemText(item);
    if (item.getItemId() == R.id.menu_clear) {
      emptyText();
      return true;
    }
    if (item.getItemId() == R.id.menu_send_broadcast) {
      sendABroadcast();
      return true;
    }
    return true;
  }

  private void appendMenuItemText(MenuItem menuItem) {
    String title = menuItem.getTitle().toString();
    tv.setText(tv.getText() + title + "\n");
  }

  private void emptyText(){
    tv.setText("");
  }

  private void sendABroadcast() {
    //Create an intent with a class component and an action
    Intent brIntent = new Intent();
    brIntent.setComponent(new ComponentName("org.feup.apm.standalonereceiver2", "org.feup.apm.standalonereceiver2.StandAloneReceiver"));
    brIntent.setAction("org.feup.apm.intents.ACTION_TESTBC_NOTIFICATION");
    brIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);      // for custom standalone BRs
    brIntent.putExtra("message", "Alert! Click to dial.");

    //send out the broadcast
    sendBroadcast(brIntent);
  }
}