package org.feup.apm.lunchlist4;

import android.database.Cursor;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

public class DetailsActivity extends AppCompatActivity {
  RestaurantsHelper helper;
  String rId;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActionBar bar = getSupportActionBar();
    if (bar != null) {
      bar.setIcon(R.drawable.rest_icon);
      bar.setDisplayShowHomeEnabled(true);
    }
    setContentView(R.layout.activity_details);
    helper = new RestaurantsHelper(this);
    findViewById(R.id.bt_save).setOnClickListener((View v)->onBtSaveClick());

    rId=getIntent().getStringExtra(MainActivity.ID_EXTRA);
    if (rId != null)
      load();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    helper.close();
  }

  void onBtSaveClick() {
    String type = "";

    switch (((RadioGroup)findViewById(R.id.rg_types)).getCheckedRadioButtonId()) {
      case R.id.sit:
        type = "sit";
        break;
      case R.id.take:
        type = "take";
        break;
      case R.id.delivery:
        type = "delivery";
        break;
    }
    if (rId==null) {
      MainActivity.currentId = helper.insert(
          ((EditText)findViewById(R.id.ed_name)).getText().toString(),
          ((EditText)findViewById(R.id.ed_address)).getText().toString(),
          type,
          ((EditText)findViewById(R.id.ed_notes)).getText().toString() );
    }
    else {
      helper.update(rId,
          ((EditText)findViewById(R.id.ed_name)).getText().toString(),
          ((EditText)findViewById(R.id.ed_address)).getText().toString(),
          type,
          ((EditText)findViewById(R.id.ed_notes)).getText().toString() );
    }
    finish();
  }

  void load() {
    Cursor c=helper.getById(rId);
    c.moveToFirst();
    ((EditText)findViewById(R.id.ed_name)).setText(helper.getName(c));
    ((EditText)findViewById(R.id.ed_address)).setText(helper.getAddress(c));
    ((EditText)findViewById(R.id.ed_notes)).setText(helper.getNotes(c));
    RadioGroup rgTypes = findViewById(R.id.rg_types);
    if (helper.getType(c).equals("sit"))
      rgTypes.check(R.id.sit);
    else if (helper.getType(c).equals("take"))
      rgTypes.check(R.id.take);
    else
      rgTypes.check(R.id.delivery);
    c.close();
  }
}
