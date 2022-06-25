package org.feup.apm.sensors;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class SensorDisplayFragment extends Fragment implements SensorEventListener {
  private static final String THETA = "\u0398";
  private static final String ACCELERATION_UNITS = "m/s\u00B2";

  private SensorManager sensorManager;
  private Sensor sensor;
  private TextView name;
  private TextView type;
  private TextView maxRange;
  private TextView minDelay;
  private TextView power;
  private TextView resolution;
  private TextView vendor;
  private TextView version;
  private TextView accuracy;
  private TextView timestampLabel;
  private TextView timestamp;
  private TextView timestampUnits;
  private TextView dataLabel;
  private TextView dataUnits;
  private TextView xAxis;
  private TextView xAxisLabel;
  private TextView yAxis;
  private TextView yAxisLabel;
  private TextView zAxis;
  private TextView zAxisLabel;
  private TextView singleValue;
  private TextView cosLabel;
  private TextView cos;
    
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View layout = inflater.inflate(R.layout.sensor_view, null);

    sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);

    name = layout.findViewById(R.id.name);
    type = layout.findViewById(R.id.type);
    maxRange = layout.findViewById(R.id.maxRange);
    minDelay = layout.findViewById(R.id.minDelay);
    power = layout.findViewById(R.id.power);
    resolution = layout.findViewById(R.id.resolution);
    vendor = layout.findViewById(R.id.vendor);
    version = layout.findViewById(R.id.version);
    accuracy = layout.findViewById(R.id.accuracy);
    timestampLabel = layout.findViewById(R.id.timestampLabel);
    timestamp = layout.findViewById(R.id.timestamp);
    timestampUnits = layout.findViewById(R.id.timestampUnits);
    dataLabel = layout.findViewById(R.id.dataLabel);
    dataUnits = layout.findViewById(R.id.dataUnits);
    xAxis = layout.findViewById(R.id.xAxis);
    xAxisLabel = layout.findViewById(R.id.xAxisLabel);
    yAxis = layout.findViewById(R.id.yAxis);
    yAxisLabel = layout.findViewById(R.id.yAxisLabel);
    zAxis = layout.findViewById(R.id.zAxis);
    zAxisLabel = layout.findViewById(R.id.zAxisLabel);
    singleValue = layout.findViewById(R.id.singleValue);
    cosLabel = layout.findViewById(R.id.cosLabel);
    cos = layout.findViewById(R.id.cos);

    layout.findViewById(R.id.delayFastest).setOnClickListener(new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            sensorManager.unregisterListener(SensorDisplayFragment.this);
            sensorManager.registerListener(SensorDisplayFragment.this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }
    });

    layout.findViewById(R.id.delayGame).setOnClickListener(new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            sensorManager.unregisterListener(SensorDisplayFragment.this);
            sensorManager.registerListener(SensorDisplayFragment.this, sensor, SensorManager.SENSOR_DELAY_GAME);
        }
    });

    layout.findViewById(R.id.delayNormal).setOnClickListener(new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            sensorManager.unregisterListener(SensorDisplayFragment.this);
            sensorManager.registerListener(SensorDisplayFragment.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    });

    layout.findViewById(R.id.delayUi).setOnClickListener(new OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            sensorManager.unregisterListener(SensorDisplayFragment.this);
            sensorManager.registerListener(SensorDisplayFragment.this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    });

    return layout;
  }

  public void displaySensor(Sensor sensor) {
    this.sensor = sensor;

    name.setText(sensor.getName());
    type.setText(String.valueOf(sensor.getType()));
    maxRange.setText(String.valueOf(sensor.getMaximumRange()));
    minDelay.setText(String.valueOf(sensor.getMinDelay()));
    power.setText(String.valueOf(sensor.getPower()));
    resolution.setText(String.valueOf(sensor.getResolution()));
    vendor.setText(String.valueOf(sensor.getVendor()));
    version.setText(String.valueOf(sensor.getVersion()));

    sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
  }

  @Override
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
    switch(accuracy) {
      case SensorManager.SENSOR_STATUS_ACCURACY_HIGH:
        this.accuracy.setText("SENSOR_STATUS_ACCURACY_HIGH");
        break;
      case SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM:
        this.accuracy.setText("SENSOR_STATUS_ACCURACY_MEDIUM");
        break;
      case SensorManager.SENSOR_STATUS_ACCURACY_LOW:
        this.accuracy.setText("SENSOR_STATUS_ACCURACY_LOW");
        break;
      case SensorManager.SENSOR_STATUS_UNRELIABLE:
        this.accuracy.setText("SENSOR_STATUS_UNRELIABLE");
        break;
    }
  }

  @Override
  public void onSensorChanged(SensorEvent event) {
    onAccuracyChanged(event.sensor, event.accuracy);

    timestampLabel.setVisibility(View.VISIBLE);
    timestamp.setVisibility(View.VISIBLE);
    timestamp.setText(String.valueOf(event.timestamp));
    timestampUnits.setVisibility(View.VISIBLE);

    switch (event.sensor.getType()) {
      case Sensor.TYPE_ACCELEROMETER:
        showEventData("Acceleration - gravity on axis",
                ACCELERATION_UNITS,
                event.values[0],
                event.values[1],
                event.values[2]);
        break;
      case Sensor.TYPE_MAGNETIC_FIELD:
        showEventData("Ambient Magnetic Field",
                "uT",
                event.values[0],
                event.values[1],
                event.values[2]);
        break;
      case Sensor.TYPE_GYROSCOPE:
        showEventData("Angular speed around axis",
                "radians/sec",
                event.values[0],
                event.values[1],
                event.values[2]);
        break;
      case Sensor.TYPE_LIGHT:
        showEventData("Ambient light",
                "lux",
                event.values[0]);
        break;
      case Sensor.TYPE_PRESSURE:
        showEventData("Atmospheric pressure",
                "hPa",
                event.values[0]);
        break;
      case Sensor.TYPE_PROXIMITY:
        showEventData("Distance",
                "cm",
                event.values[0]);
        break;
      case Sensor.TYPE_GRAVITY:
        showEventData("Gravity",
                ACCELERATION_UNITS,
                event.values[0],
                event.values[1],
                event.values[2]);
        break;
      case Sensor.TYPE_LINEAR_ACCELERATION:
        showEventData("Acceleration (not including gravity)",
                ACCELERATION_UNITS,
                event.values[0],
                event.values[1],
                event.values[2]);
        break;
      case Sensor.TYPE_ROTATION_VECTOR:
        showEventData("Rotation Vector",
                null,
                event.values[0],
                event.values[1],
                event.values[2]);

        xAxisLabel.setText("x*sin(" + THETA + "/2)");
        yAxisLabel.setText("y*sin(" + THETA + "/2)");
        zAxisLabel.setText("z*sin(" + THETA + "/2)");

        if (event.values.length == 4) {
          cosLabel.setVisibility(View.VISIBLE);
          cos.setVisibility(View.VISIBLE);
          cos.setText(String.valueOf(event.values[3]));
        }

        break;
      case Sensor.TYPE_ORIENTATION:
        showEventData("Angle",
                "Degrees",
                event.values[0],
                event.values[1],
                event.values[2]);

        xAxisLabel.setText(R.string.azimuthLabel);
        yAxisLabel.setText(R.string.pitchLabel);
        zAxisLabel.setText(R.string.rollLabel);

        break;
      case Sensor.TYPE_RELATIVE_HUMIDITY:
        showEventData("Relative ambient air humidity",
                "%",
                event.values[0]);
        break;
      case Sensor.TYPE_TEMPERATURE:
      case Sensor.TYPE_AMBIENT_TEMPERATURE:
        showEventData("Ambient temperature",
                "degrees Celsius",
                event.values[0]);
        break;
    }
  }

  private void showEventData(String label, String units, float x, float y, float z) {
    dataLabel.setVisibility(View.VISIBLE);
    dataLabel.setText(label);

    if (units == null) {
      dataUnits.setVisibility(View.GONE);
    }
    else {
      dataUnits.setVisibility(View.VISIBLE);
      dataUnits.setText("(" + units + "):");
    }

    singleValue.setVisibility(View.GONE);

    xAxisLabel.setVisibility(View.VISIBLE);
    xAxisLabel.setText(R.string.xAxisLabel);
    xAxis.setVisibility(View.VISIBLE);
    xAxis.setText(String.valueOf(x));

    yAxisLabel.setVisibility(View.VISIBLE);
    yAxisLabel.setText(R.string.yAxisLabel);
    yAxis.setVisibility(View.VISIBLE);
    yAxis.setText(String.valueOf(y));

    zAxisLabel.setVisibility(View.VISIBLE);
    zAxisLabel.setText(R.string.zAxisLabel);
    zAxis.setVisibility(View.VISIBLE);
    zAxis.setText(String.valueOf(z));
  }

  private void showEventData(String label, String units, float value) {
    dataLabel.setVisibility(View.VISIBLE);
    dataLabel.setText(label);

    dataUnits.setVisibility(View.VISIBLE);
    dataUnits.setText("(" + units + "):");

    singleValue.setVisibility(View.VISIBLE);
    singleValue.setText(String.valueOf(value));

    xAxisLabel.setVisibility(View.GONE);
    xAxis.setVisibility(View.GONE);

    yAxisLabel.setVisibility(View.GONE);
    yAxis.setVisibility(View.GONE);

    zAxisLabel.setVisibility(View.GONE);
    zAxis.setVisibility(View.GONE);
  }

  @Override
  public void onHiddenChanged(boolean hidden) {
    super.onHiddenChanged(hidden);

    if (hidden) {
      sensorManager.unregisterListener(this);
    }
  }

  @Override
  public void onPause() {
    super.onPause();
    sensorManager.unregisterListener(this);
  }
}
