package org.feup.apm.lunchlist3b;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
  public final static String ID_EXTRA="org.feup.apm.lunchlist.POS";
  LunchApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    ActionBar bar = getSupportActionBar();
    if (bar != null) {
      bar.setIcon(R.drawable.rest_icon);
      bar.setDisplayShowHomeEnabled(true);
    }
    setContentView(R.layout.activity_main);

    ListView list = findViewById(R.id.listview);
    app = (LunchApp)getApplicationContext();
    app.adapter=new RestaurantAdapter();
    list.setAdapter(app.adapter);
    list.setEmptyView(findViewById(R.id.empty_list));
    list.setOnItemClickListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    new MenuInflater(this).inflate(R.menu.main, menu);
    return (super.onCreateOptionsMenu(menu));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId()==R.id.toast) {
      String message="No restaurant selected";
      if (app.current!=null)
        message=String.format("%s:\n%s", app.current.getName(), app.current.getNotes());
      Toast.makeText(this, message, Toast.LENGTH_LONG).show();
      return(true);
    }
    else if (item.getItemId() == R.id.add) {
      startActivity(new Intent(this, DetailsActivity.class));
      return(true);
    }
    return(super.onOptionsItemSelected(item));
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
    Intent i=new Intent(this, DetailsActivity.class);
    app.current = app.restaurants.get(pos);
    i.putExtra(ID_EXTRA, pos);
    startActivity(i);
  }

  /*                                              *
   * Sub class with the custom ListView adapter   *
   *                                              */

  class RestaurantAdapter extends ArrayAdapter<Restaurant> {
    RestaurantAdapter() {
      super(MainActivity.this, R.layout.row, app.restaurants);
    }

    @Override
    public @NonNull View getView(int position, View convertView, @NonNull ViewGroup parent) {
      View row = convertView;   // if Android is recycling
      if (row == null)
        row = getLayoutInflater().inflate(R.layout.row, parent, false);    // get our custom layout
      Restaurant r = app.restaurants.get(position);
      ((TextView)row.findViewById(R.id.title)).setText(r.getName());      // fill restaurant name
      ((TextView)row.findViewById(R.id.address)).setText(r.getAddress());      // fill restaurant address
      ImageView symbol = row.findViewById(R.id.symbol);                   // choose an icon for type
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
