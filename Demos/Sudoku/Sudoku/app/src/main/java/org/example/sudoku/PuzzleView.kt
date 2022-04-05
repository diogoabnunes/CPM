package org.example.sudoku

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.animation.AnimationUtils

class PuzzleView(context: Context) : View(context) {
  private var width = 0f  // width of one tile
  private var height = 0f // height of one tile
  private var selX = 0    // X index of selection
  private var selY = 0    // Y index of selection
  private val c = IntArray(3) // colors for the hints
  private val background: Paint
  private val foreground: Paint
  private val dark: Paint
  private val light: Paint
  private val hilite: Paint
  private val hint: Paint
  private val selected: Paint
  private val r = Rect()
  private val selRect = Rect()
  private val game: Game

  override fun onSaveInstanceState(): Parcelable? {
    val p = super.onSaveInstanceState()
    Log.d(TAG, "onSaveInstanceState")
    val bundle = Bundle()
    bundle.putInt(SELX, selX)
    bundle.putInt(SELY, selY)
    bundle.putParcelable(VIEW_STATE, p)
    return bundle
  }

  override fun onRestoreInstanceState(state: Parcelable) {
    Log.d(TAG, "onRestoreInstanceState")
    val bundle = state as Bundle
    select(bundle.getInt(SELX), bundle.getInt(SELY))
    super.onRestoreInstanceState(bundle.getParcelable(VIEW_STATE))
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    width = w / 9f
    height = h / 9f
    foreground.textSize = height * 0.75f
    foreground.textScaleX = width / height
    getRect(selX, selY, selRect)
    Log.d(TAG, "onSizeChanged: width $width, height $height")
    super.onSizeChanged(w, h, oldw, oldh)
  }

  override fun onDraw(canvas: Canvas) {
    // Draw the background...
    canvas.drawRect(0f, 0f, getWidth().toFloat(), getHeight().toFloat(), background)
    // Draw the board...
    // Draw the minor grid lines
    for (i in 0..8) {
      canvas.drawLine(0f, i * height, getWidth().toFloat(), i * height, light)
      canvas.drawLine(0f, i * height + 1, getWidth().toFloat(), i * height + 1, hilite)
      canvas.drawLine(i * width, 0f, i * width, getHeight().toFloat(), light)
      canvas.drawLine(i * width + 1, 0f, i * width + 1, getHeight().toFloat(), hilite)
    }
    // Draw the major grid lines
    for (i in 0..8) {
      if (i % 3 != 0) continue
      canvas.drawLine(0f, i * height, getWidth().toFloat(), i * height, dark)
      canvas.drawLine(0f, i * height + 1, getWidth().toFloat(), i * height + 1, hilite)
      canvas.drawLine(i * width, 0f, i * width, getHeight().toFloat(), dark)
      canvas.drawLine(i * width + 1, 0f, i * width + 1, getHeight().toFloat(), hilite)
    }
    // Draw the numbers...
    // Draw the number in the center of the tile
    val fm = foreground.fontMetrics
    // Centering in X: use alignment (and X at midpoint)
    val x = width / 2
    // Centering in Y: measure ascent/descent first
    val y = height / 2 - (fm.ascent + fm.descent) / 2
    for (i in 0..8) {
      for (j in 0..8) canvas.drawText(
        game.getTileString(i, j),
        i * width + x,
        j * height + y,
        foreground
      )
    }
    if (Prefs.getHints(context)) {
      // Draw the hints...
      // Pick a hint color based on #moves left
      for (i in 0..8) {
        for (j in 0..8) {
          val movesleft: Int = 9 - game.getUsedTiles(i, j)!!.size
          if (movesleft < c.size) {
            getRect(i, j, r)
            hint.color = c[movesleft]
            canvas.drawRect(r, hint)
          }
        }
      }
    }
    // Draw the selection...
    Log.d(TAG, "selRect=$selRect")
    canvas.drawRect(selRect, selected)
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    if (event.action != MotionEvent.ACTION_DOWN) return super.onTouchEvent(event)
    select((event.x / width).toInt(), (event.y / height).toInt())
    game.showKeypadOrError(selX, selY)
    Log.d(TAG, "onTouchEvent: x $selX, y $selY")
    return true
  }

  override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
    Log.d(TAG, "onKeyDown: keycode=$keyCode, event=$event")
    when (keyCode) {
      KeyEvent.KEYCODE_DPAD_UP -> select(selX, selY - 1)
      KeyEvent.KEYCODE_DPAD_DOWN -> select(selX, selY + 1)
      KeyEvent.KEYCODE_DPAD_LEFT -> select(selX - 1, selY)
      KeyEvent.KEYCODE_DPAD_RIGHT -> select(selX + 1, selY)
      KeyEvent.KEYCODE_0, KeyEvent.KEYCODE_SPACE -> setSelectedTile(0)
      KeyEvent.KEYCODE_1 -> setSelectedTile(1)
      KeyEvent.KEYCODE_2 -> setSelectedTile(2)
      KeyEvent.KEYCODE_3 -> setSelectedTile(3)
      KeyEvent.KEYCODE_4 -> setSelectedTile(4)
      KeyEvent.KEYCODE_5 -> setSelectedTile(5)
      KeyEvent.KEYCODE_6 -> setSelectedTile(6)
      KeyEvent.KEYCODE_7 -> setSelectedTile(7)
      KeyEvent.KEYCODE_8 -> setSelectedTile(8)
      KeyEvent.KEYCODE_9 -> setSelectedTile(9)
      KeyEvent.KEYCODE_ENTER, KeyEvent.KEYCODE_DPAD_CENTER -> game.showKeypadOrError(selX, selY)
      else -> return super.onKeyDown(keyCode, event)
    }
    return true
  }

  fun setSelectedTile(tile: Int) {
    if (game.setTileIfValid(selX, selY, tile)) {
      invalidate() // may change hints
    } else {
      // Number is not valid for this tile
      Log.d(TAG, "setSelectedTile: invalid: $tile")
      startAnimation(AnimationUtils.loadAnimation(game, R.anim.shake))
    }
  }

  private fun select(x: Int, y: Int) {
    invalidate(selRect)
    selX = Math.min(Math.max(x, 0), 8)
    selY = Math.min(Math.max(y, 0), 8)
    getRect(selX, selY, selRect)
    invalidate(selRect)
  }

  private fun getRect(x: Int, y: Int, rect: Rect) {
    rect[(x * width).toInt(), (y * height).toInt(), (x * width + width).toInt()] =
      (y * height + height).toInt()
  }

  companion object {
    private const val TAG = "Sudoku"
    private const val SELX = "selX"
    private const val SELY = "selY"
    private const val VIEW_STATE = "viewState"
    private const val ID = 42
  }

  init {
    game = context as Game
    isFocusable = true
    isFocusableInTouchMode = true
    id = ID
    background = Paint()
    background.color = resources.getColor(R.color.puzzle_background)
    // Define color and style for numbers
    foreground = Paint(Paint.ANTI_ALIAS_FLAG)
    foreground.color = resources.getColor(R.color.puzzle_foreground)
    foreground.style = Paint.Style.FILL
    foreground.textAlign = Paint.Align.CENTER
    // Define colors for the grid lines
    dark = Paint()
    dark.color = resources.getColor(R.color.puzzle_dark)
    hilite = Paint()
    hilite.color = resources.getColor(R.color.puzzle_hilite)
    light = Paint()
    light.color = resources.getColor(R.color.puzzle_light)
    // For the hints
    hint = Paint()
    c[0] = resources.getColor(R.color.puzzle_hint_0)
    c[1] = resources.getColor(R.color.puzzle_hint_1)
    c[2] = resources.getColor(R.color.puzzle_hint_2)
    //Selected position
    selected = Paint()
    selected.color = resources.getColor(R.color.puzzle_selected)
  }
}