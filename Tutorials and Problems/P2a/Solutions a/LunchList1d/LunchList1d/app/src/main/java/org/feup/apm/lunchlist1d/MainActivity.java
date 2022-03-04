package org.feup.apm.lunchlist1d;

import android.app.Activity;
import android.content.Context;
import androidx.annotation.NonNull;
import com.google.android.material.tabs.TabLayout;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
                                                               TabLayout.OnTabSelectedListener {
  List<Restaurant> rests = new ArrayList<>();
  ArrayAdapter<Restaurant> adapter;
  TabLayout.Tab listTab, details;
  View tab1, tab2;

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
    adapter = new RestaurantAdapter();
    list.setAdapter(adapter);
    list.setOnItemClickListener(this);

    TabLayout tabs = findViewById(R.id.tabs);
    listTab = tabs.newTab().setText("List");
    tabs.addTab(listTab);
    details = tabs.newTab().setText("Details");
    tabs.addTab(details);
    tabs.addOnTabSelectedListener(this);
    tab1 = list;
    tab2 = findViewById(R.id.rest_params);
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
    clearKeyboard(this);   // hide the soft keyboard, if present
    listTab.select();     // switch to the list tab
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
    details.select();                // switch to the details tab
  }

  @Override
  public void onTabSelected(TabLayout.Tab tab) {
    switch (tab.getPosition()) {
      case 0:
        tab1.setVisibility(View.VISIBLE);
        break;
      case 1:
        tab2.setVisibility(View.VISIBLE);
        break;
    }
  }

  @Override
  public void onTabUnselected(TabLayout.Tab tab) {
    switch (tab.getPosition()) {
      case 0:
        tab1.setVisibility(View.INVISIBLE);
        break;
      case 1:
        tab2.setVisibility(View.INVISIBLE);
        break;
    }
  }

  @Override
  public void onTabReselected(TabLayout.Tab tab) {
  }

  void clearKeyboard(Activity act) {
    View view = act.findViewById(android.R.id.content);
    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
    if (imm != null)
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }

  /*                                              *
   * Sub class with the custom ListView adapter   *
   *                                              */

  class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    RestaurantAdapter() {
      super(MainActivity.this, R.layout.row, rests);
    }

    @Override
    public @NonNull View getView(int position, View convertView, @NonNull ViewGroup parent) {
      View row = convertView;
      if (row == null) {
        LayoutInflater inflater = getLayoutInflater();
        row = inflater.inflate(R.layout.row, parent, false);    // get our custom layout
      }
      Restaurant r = rests.get(position);
      ((TextView)row.findViewById(R.id.title)).setText(r.getName());      // fill restaurant name
      ((TextView)row.findViewById(R.id.address)).setText(r.getAddress());      // fill restaurant address
      ImageView symbol = row.findViewById(R.id.symbol);
      if (r.getType().equals("sit"))
        symbol.setImageResource(R.drawable.ball_red);
      else if (r.getType().equals("take"))
        symbol.setImageResource(R.drawable.ball_yellow);
      else
        symbol.setImageResource((R.drawable.ball_green));
      return (row);
    }
  }
}
