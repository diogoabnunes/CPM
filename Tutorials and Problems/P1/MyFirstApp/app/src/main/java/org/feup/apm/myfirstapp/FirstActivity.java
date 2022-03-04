package org.feup.apm.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity {
  public final static String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";     //To be unique.

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_first);
    findViewById(R.id.button_send).setOnClickListener(this::btSendOnClick);
  }

  public void btSendOnClick(View view) {
    Intent intent = new Intent(this, SecondActivity.class);
    EditText editText = findViewById(R.id.edit_message);
    String message = editText.getText().toString();
    intent.putExtra(EXTRA_MESSAGE, message);
    startActivity(intent);
  }
}