package org.feup.apm.sensors;

import android.app.Activity;
import android.os.Bundle;

public class SensorListActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.sensor_main);

    // wire up the fragments so selector can call display
    SensorDisplayFragment sensorDisplay = (SensorDisplayFragment) getFragmentManager().findFragmentById(R.id.frag_sensor_view);
    SensorSelectorFragment sensorSelect = (SensorSelectorFragment) getFragmentManager().findFragmentById(R.id.frag_sensor_select);
    sensorSelect.setSensorDisplay(sensorDisplay);
  }
}
