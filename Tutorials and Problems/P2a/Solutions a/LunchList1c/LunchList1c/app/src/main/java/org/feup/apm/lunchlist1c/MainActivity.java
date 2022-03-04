package org.feup.apm.lunchlist1c;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
  List<Restaurant> rests = new ArrayList<>();
  ArrayAdapter<Restaurant> adapter;

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

    ListView list = findViewById(R.id.listview);
    adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rests);
    list.setAdapter(adapter);
    list.setOnItemClickListener(this);
  }

  void onBtSaveClick() {
    Restaurant r = new Restaurant();
    r.setName(((EditText)findViewById(R.id.ed_name)).getText().toString());
    r.setAddress(((EditText)findViewById(R.id.ed_address)).getText().toString());
    switch (((RadioGroup)findViewById(R.id.rg_types)).getCheckedRadioButtonId()) {
      case R.id.rb_take:
        r.setType("take");
        break;
      case R.id.rb_sit:
        r.setType("sit");
        break;
      case R.id.rb_delivery:
        r.setType("delivery");
        break;
    }
    adapter.add(r);
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
    Restaurant r = rests.get(pos);

    ((EditText)findViewById(R.id.ed_name)).setText(r.getName());
    ((EditText)findViewById(R.id.ed_address)).setText(r.getAddress());
    RadioGroup types = findViewById(R.id.rg_types);

    if (r.getType().equals("sit")) {
      types.check(R.id.rb_sit);
    }
    else if (r.getType().equals("take")) {
      types.check(R.id.rb_take);
    }
    else {
      types.check(R.id.rb_delivery);
    }
  }
}
