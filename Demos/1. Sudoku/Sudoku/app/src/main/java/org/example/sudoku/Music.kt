package org.example.sudoku

import android.content.Context
import android.media.MediaPlayer

object Music {
  private var mp: MediaPlayer? = null

  /** Stop old song and start new one  */
  fun play(context: Context?, resource: Int) {
    stop(context)
    // Start music only if not disabled in preferences
    if (Prefs.getMusic(context)) {
      mp = MediaPlayer.create(context, resource)
      mp!!.setLooping(true)
      mp!!.start()
    }
  }

  /** Stop the music  */
  fun stop(context: Context?) {
    if (mp != null) {
      mp!!.stop()
      mp!!.release()
      mp = null
    }
  }
}