package org.feup.apm.lunchlist1a;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ActionBar bar = getSupportActionBar();
    if (bar != null) {
      bar.setIcon(R.drawable.rest_icon);
      bar.setDisplayShowHomeEnabled(true);
    }
    setContentView(R.layout.activity_main);
    findViewById(R.id.bt_save).setOnClickListener((View v)->onBtSaveClick());
  }

  void onBtSaveClick() {
    Restaurant r = new Restaurant();
    r.setName(((EditText)findViewById(R.id.ed_name)).getText().toString());
    r.setAddress(((EditText)findViewById(R.id.ed_address)).getText().toString());
  }

}
