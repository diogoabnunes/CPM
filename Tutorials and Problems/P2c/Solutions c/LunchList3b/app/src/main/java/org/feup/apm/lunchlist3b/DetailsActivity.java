package org.feup.apm.lunchlist3b;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DetailsActivity extends AppCompatActivity {
  LunchApp app;
  int rPos;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActionBar bar = getSupportActionBar();
    if (bar != null) {
      bar.setIcon(R.drawable.rest_icon);
      bar.setDisplayShowHomeEnabled(true);
    }
    setContentView(R.layout.activity_details);
    app = (LunchApp)getApplicationContext();
    findViewById(R.id.bt_save).setOnClickListener((View v)->onBtSaveClick());

    rPos=getIntent().getIntExtra(MainActivity.ID_EXTRA, -1);
    if (rPos != -1)
      load();
  }

  void onBtSaveClick() {
    String type = "";

    switch (((RadioGroup)findViewById(R.id.rg_types)).getCheckedRadioButtonId()) {
      case R.id.sit:
        type = "sit";
        break;
      case R.id.take:
        type="take";
        break;
      case R.id.delivery:
        type="delivery";
        break;
    }
    if (rPos!=-1)
      app.adapter.remove(app.current);
    app.current = new Restaurant();
    app.current.setName(((EditText)findViewById(R.id.ed_name)).getText().toString());
    app.current.setAddress(((EditText)findViewById(R.id.ed_address)).getText().toString());
    app.current.setNotes(((EditText)findViewById(R.id.ed_notes)).getText().toString());
    app.current.setType(type);
    if (rPos!=-1)
      app.adapter.insert(app.current, rPos);
    else
      app.adapter.add(app.current);
    finish();
  }

  void load() {
    ((EditText)findViewById(R.id.ed_name)).setText(app.current.getName());
    ((EditText)findViewById(R.id.ed_address)).setText(app.current.getAddress());
    ((EditText)findViewById(R.id.ed_notes)).setText(app.current.getNotes());
    RadioGroup rgTypes = findViewById(R.id.rg_types);
    if (app.current.getType().equals("sit"))
      rgTypes.check(R.id.sit);
    else if (app.current.getType().equals("take"))
      rgTypes.check(R.id.take);
    else
      rgTypes.check(R.id.delivery);
  }
}
