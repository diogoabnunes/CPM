package org.example.sudoku

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.View

class Keypad(context: Context?, private val useds: IntArray, private val puzzleView: PuzzleView) : Dialog(context!!) {
  private val keys = arrayOfNulls<View>(9)
  private var keypad: View? = null
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setTitle(R.string.keypad_title)
    setContentView(R.layout.keypad)
    findViews()
    for (element in useds) {
      if (element != 0) keys[element - 1]!!.visibility = View.INVISIBLE
    }
    setListeners()
  }

  override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
    var tile = 0
    tile = when (keyCode) {
      KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_SPACE -> 0
      KeyEvent.KEYCODE_1 -> 1
      KeyEvent.KEYCODE_2 -> 2
      KeyEvent.KEYCODE_3 -> 3
      KeyEvent.KEYCODE_4 -> 4
      KeyEvent.KEYCODE_5 -> 5
      KeyEvent.KEYCODE_6 -> 6
      KeyEvent.KEYCODE_7 -> 7
      KeyEvent.KEYCODE_8 -> 8
      KeyEvent.KEYCODE_9 -> 9
      else -> return super.onKeyDown(keyCode, event)
    }
    if (isValid(tile)) {
      returnResult(tile)
    }
    return true
  }

  /** Return the chosen tile to the caller  */
  private fun returnResult(tile: Int) {
    puzzleView.setSelectedTile(tile)
    dismiss()
  }

  private fun isValid(tile: Int): Boolean {
    for (t in useds) {
      if (tile == t) return false
    }
    return true
  }

  private fun findViews() {
    keypad = findViewById(R.id.keypad)
    keys[0] = findViewById(R.id.keypad_1)
    keys[1] = findViewById(R.id.keypad_2)
    keys[2] = findViewById(R.id.keypad_3)
    keys[3] = findViewById(R.id.keypad_4)
    keys[4] = findViewById(R.id.keypad_5)
    keys[5] = findViewById(R.id.keypad_6)
    keys[6] = findViewById(R.id.keypad_7)
    keys[7] = findViewById(R.id.keypad_8)
    keys[8] = findViewById(R.id.keypad_9)
  }

  private fun setListeners() {
    for (i in keys.indices) {
      val t = i + 1
      keys[i]!!.setOnClickListener { returnResult(t) }
    }
    keypad!!.setOnClickListener { returnResult(0) }
  }

  companion object {
    protected const val TAG = "Sudoku"
  }
}