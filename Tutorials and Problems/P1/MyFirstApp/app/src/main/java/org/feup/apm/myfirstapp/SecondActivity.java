package org.feup.apm.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Intent intent = getIntent();
    String message = intent.getStringExtra(FirstActivity.EXTRA_MESSAGE);

    TextView tv_message = new TextView(this);
    tv_message.setTextSize(30f);
    tv_message.setText(message);

    setContentView(tv_message);
  }
}