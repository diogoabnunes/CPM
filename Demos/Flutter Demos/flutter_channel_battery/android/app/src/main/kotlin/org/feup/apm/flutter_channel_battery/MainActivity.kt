package org.feup.apm.flutter_channel_battery

import android.content.BroadcastReceiver
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.content.IntentFilter
import android.os.BatteryManager
import android.os.Build
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.EventChannel
import io.flutter.plugin.common.MethodChannel

class MainActivity: FlutterActivity() {
  private val levelCHANNEL = "bat.channel/battery"
  private val statusCHANNEL = "bat.channel/charging"

  override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
    super.configureFlutterEngine(flutterEngine)
    MethodChannel(flutterEngine.dartExecutor, levelCHANNEL).setMethodCallHandler { call, result ->
      if (call.method == "getBatteryLevel") {
        val batteryLevel = getBatteryLevel()
        if (batteryLevel != -1)
          result.success(batteryLevel)
        else
          result.error("UNAVAILABLE: Cannot get Battery level", null, null)
      }
      else
        result.notImplemented()
    }
    EventChannel(flutterEngine.dartExecutor, statusCHANNEL).setStreamHandler(object: EventChannel.StreamHandler {
        private var chargingStateChangeReceiver: BroadcastReceiver? = null

        override fun onListen(arguments: Any?, events: EventChannel.EventSink) {
          chargingStateChangeReceiver = createChargingStateChangeReceiver(events)
          registerReceiver(chargingStateChangeReceiver, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
        }

        override fun onCancel(arguments: Any?) {
          unregisterReceiver(chargingStateChangeReceiver)
          chargingStateChangeReceiver = null
        }
      }
    )
  }

  private fun createChargingStateChangeReceiver(events: EventChannel.EventSink): BroadcastReceiver {
    return object: BroadcastReceiver() {
      override fun onReceive(context: Context, intent: Intent) {
        val status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1)
        if (status == BatteryManager.BATTERY_STATUS_UNKNOWN)
          events.error("UNAVAILABLE: Status not supported", null, null)
        else {
          val isCharging =
            status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL
          events.success(if (isCharging) "charging" else "discharging")
        }
      }
    }
  }

  private fun getBatteryLevel(): Int {
    val batteryLevel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      val batteryManager = getSystemService(Context.BATTERY_SERVICE) as BatteryManager
      batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    } else {
      val intent = ContextWrapper(applicationContext).registerReceiver(null, IntentFilter(Intent.ACTION_BATTERY_CHANGED))
      intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL, -1)*100 / intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1)
    }
    return batteryLevel
  }
}
