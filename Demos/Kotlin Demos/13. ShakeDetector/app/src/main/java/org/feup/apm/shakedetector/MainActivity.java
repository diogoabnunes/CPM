package org.feup.apm.shakedetector;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ShakeDetector.OnShakeDetectedListener {
  LinearLayout root;
  TextView tvSensorName;
  boolean shake = false;
  ShakeDetector shakeDetector;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    root = (LinearLayout) findViewById(R.id.root);
    root.setBackgroundResource(R.color.colorShake1);
    tvSensorName = (TextView) findViewById(R.id.tvSName);
    shakeDetector = new ShakeDetector(this);
    shakeDetector.setOnShakeDetectedListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    String sName = shakeDetector.startSensing();
    if (sName != null)
      tvSensorName.setText("("+sName+")");
    else {
      Toast.makeText(this, "There is no linear accel sensor!", Toast.LENGTH_LONG).show();
      finish();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();
    shakeDetector.stopSensing();
  }

  public void toggleBackground() {
    if (shake)
      root.setBackgroundResource(R.color.colorShake1);
    else
      root.setBackgroundResource(R.color.colorShake2);
    shake = !shake;
  }

  public void onShakeDetected() {
    toggleBackground();
  }
}