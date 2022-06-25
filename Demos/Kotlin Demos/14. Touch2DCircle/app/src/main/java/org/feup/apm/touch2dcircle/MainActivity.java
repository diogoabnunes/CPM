package org.feup.apm.touch2dcircle;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
  TouchView screen;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    screen = new TouchView(this);
    setContentView(screen);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    MenuItem item;
    SpannableStringBuilder builder;

    getMenuInflater().inflate(R.menu.menu_main, menu);

    item = menu.findItem(R.id.action_color_red);
    builder = new SpannableStringBuilder("* red");
    builder.setSpan(new ImageSpan(this, R.drawable.ic_action_red), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    item.setTitle(builder);

    item = menu.findItem(R.id.action_color_green);
    builder = new SpannableStringBuilder("* green");
    builder.setSpan(new ImageSpan(this, R.drawable.ic_action_green), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    item.setTitle(builder);

    item = menu.findItem(R.id.action_color_blue);
    builder = new SpannableStringBuilder("* blue");
    builder.setSpan(new ImageSpan(this, R.drawable.ic_action_blue), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    item.setTitle(builder);

    item = menu.findItem(R.id.action_color_yellow);
    builder = new SpannableStringBuilder("* yellow");
    builder.setSpan(new ImageSpan(this, R.drawable.ic_action_yellow), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    item.setTitle(builder);

    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_color_red:
        screen.changeColor(Color.RED);
        break;
      case R.id.action_color_green:
        screen.changeColor(Color.GREEN);
        break;
      case R.id.action_color_blue:
        screen.changeColor(Color.BLUE);
        break;
      case R.id.action_color_yellow:
        screen.changeColor(Color.YELLOW);
        break;
      default:
        return false;
    }
    return true;
  }
}