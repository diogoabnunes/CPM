package org.feup.apm.fragmentplanets

import android.app.Activity
import android.app.Application
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log

class MyApp: Application() {
  private var pos = 0
  private var mp: MediaPlayer? = null

  override fun onCreate() {
    super.onCreate()
    registerActivityLifecycleCallbacks(AppLifecycleTracker())
  }

  inner class AppLifecycleTracker : Application.ActivityLifecycleCallbacks {
    private var numStarted = 0

    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
      Log.d("App", "Created: ${p1 == null}")
    }

    override fun onActivityStarted(p0: Activity) {
      Log.d("App", "Start")
      if (numStarted == 0) {
        mp = MediaPlayer.create(p0, R.raw.mars_exc)
        mp!!.seekTo(pos)
        mp!!.isLooping = true
        mp!!.start()
      }
      numStarted++
    }

    override fun onActivityResumed(p0: Activity) {
    }

    override fun onActivityPaused(p0: Activity) {
    }

    override fun onActivityStopped(p0: Activity) {
      Log.d("App", "Stop")
      numStarted--
      if (numStarted == 0) {
        pos = mp!!.currentPosition
        mp!!.stop()
        mp!!.release()
      }
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
      Log.d("App", "Save")
    }

    override fun onActivityDestroyed(p0: Activity) {
    }
  }
}