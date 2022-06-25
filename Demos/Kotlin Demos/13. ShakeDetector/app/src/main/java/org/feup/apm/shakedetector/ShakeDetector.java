package org.feup.apm.shakedetector;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import androidx.appcompat.app.AppCompatActivity;

public class ShakeDetector implements SensorEventListener {
  public interface OnShakeDetectedListener {
    void onShakeDetected();
  }

  private static final int MIN_SHAKE_ACCELERATION = 5;
  private static final int MIN_MOVEMENTS = 4;
  private static final int MAX_SHAKE_DURATION = 500;

  private OnShakeDetectedListener listener=null;
  private SensorManager sm;

  private static final int X = 0;
  private static final int Y = 0;
  private static final int Z = 0;

  private long startTime = 0;
  private int moveCount = 0;

  ShakeDetector(AppCompatActivity activity) {
    sm = (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
  }

  public String startSensing() {
    String name = null;

    Sensor linAccel = sm.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
    if (linAccel != null) {
      sm.registerListener(this, linAccel, SensorManager.SENSOR_DELAY_UI);
      name = linAccel.getName();
    }
    return name;
  }

  public void stopSensing() {
    sm.unregisterListener(this);
  }

  public void setOnShakeDetectedListener(OnShakeDetectedListener listener) {
    this.listener = listener;
  }

  public void onSensorChanged(SensorEvent event) {
    float maxAccel = getMaxCurrentLinearAcceleration(event.values);
    if (maxAccel > MIN_SHAKE_ACCELERATION) {
      long now = System.currentTimeMillis();
      if (startTime == 0)
        startTime = now;
      long elapsedTime = now - startTime;
      if (elapsedTime > MAX_SHAKE_DURATION)
        resetShakes();
      else {
        moveCount++;
        if (moveCount >= MIN_MOVEMENTS) {
          if (listener != null)
            listener.onShakeDetected();
          resetShakes();
        }
      }
    }
  }

  public void onAccuracyChanged(Sensor sensor, int accuracy) {
  }

  private float getMaxCurrentLinearAcceleration(float[] acceleration) {
    float maxVal = Math.abs(acceleration[X]);
    if (Math.abs(acceleration[Y]) > maxVal)
      maxVal = Math.abs(acceleration[Y]);
    if (Math.abs(acceleration[Z]) > maxVal)
      maxVal = Math.abs(acceleration[Z]);
    return maxVal;
  }

  private void resetShakes() {
    startTime = 0;
    moveCount = 0;
  }
}
