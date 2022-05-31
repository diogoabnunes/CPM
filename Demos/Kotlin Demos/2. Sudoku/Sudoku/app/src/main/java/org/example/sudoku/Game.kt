package org.example.sudoku

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.example.sudoku.Music.play
import org.example.sudoku.Music.stop

class Game : AppCompatActivity() {
  private var puzzle = IntArray(9 * 9)
  private val easyPuzzle = "360000000004230800000004200" +
      "070460003820000014500013020" +
      "001900000007048300000000045"
  private val mediumPuzzle = "650000070000506000014000005" +
      "007009000002314700000700800" +
      "500000630000201000030000097"
  private val hardPuzzle = "009000000080605020501078000" +
      "000000700706040102004000000" +
      "000720903090301080000000600"
  private var puzzleView: PuzzleView? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate")
    val diff = intent.getIntExtra(KEY_DIFFICULTY, DIFFICULTY_EASY)
    puzzle = getPuzzle(diff)
    calculateUsedTiles()
    puzzleView = PuzzleView(this)
    setContentView(puzzleView)
    puzzleView!!.requestFocus()

    // If the activity is restarted, do a continue next time
    intent.putExtra(KEY_DIFFICULTY, DIFFICULTY_CONTINUE)
  }

  override fun onResume() {
    super.onResume()
    play(this, R.raw.game)
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause")
    stop(this)

    // Save the current puzzle
    getPreferences(MODE_PRIVATE).edit().putString(PREF_PUZZLE, toPuzzleString(puzzle)).apply()
  }

  /** Given a difficulty level, come up with a new puzzle  */
  private fun getPuzzle(diff: Int): IntArray {
    val puz = when (diff) {
      DIFFICULTY_CONTINUE -> getPreferences(MODE_PRIVATE).getString(PREF_PUZZLE, easyPuzzle)
      DIFFICULTY_HARD -> hardPuzzle
      DIFFICULTY_MEDIUM -> mediumPuzzle
      DIFFICULTY_EASY -> easyPuzzle
      else -> easyPuzzle
    }
    return fromPuzzleString(puz)
  }

  /** Return the tile at the given coordinates  */
  private fun getTile(x: Int, y: Int): Int {
    return puzzle[y * 9 + x]
  }

  /** Change the tile at the given coordinates  */
  private fun setTile(x: Int, y: Int, value: Int) {
    puzzle[y * 9 + x] = value
  }

  /** Return a string for the tile at the given coordinates  */
  fun getTileString(x: Int, y: Int): String {
    val v = getTile(x, y)
    return if (v == 0) "" else v.toString()
  }

  /** Change the tile only if it's a valid move  */
  fun setTileIfValid(x: Int, y: Int, value: Int): Boolean {
    val tiles = getUsedTiles(x, y)
    if (value != 0) {
      for (tile in tiles!!) {
        if (tile == value) return false
      }
    }
    setTile(x, y, value)
    calculateUsedTiles()
    return true
  }

  /** Open the keypad if there are any valid moves  */
  fun showKeypadOrError(x: Int, y: Int) {
    val tiles = getUsedTiles(x, y)
    if (tiles!!.size == 9) {
      val toast = Toast.makeText(this, R.string.no_moves_label, Toast.LENGTH_SHORT)
      toast.setGravity(Gravity.CENTER, 0, 0)
      toast.show()
    } else {
      Log.d(TAG, "showKeypad: used=" + toPuzzleString(tiles))
      val v: Dialog = Keypad(this, tiles, puzzleView!!)
      v.show()
    }
  }

  /** Cache of used tiles  */
  private val used = Array(9) {
    arrayOfNulls<IntArray>(9)
  }

  /** Return cached used tiles visible from the given coords  */
  fun getUsedTiles(x: Int, y: Int): IntArray? {
    return used[x][y]
  }

  /** Compute the two dimensional array of used tiles  */
  private fun calculateUsedTiles() {
    for (x in 0..8) {
      for (y in 0..8) {
        used[x][y] = calculateUsedTiles(x, y)
        // Log.d(TAG, "used[" + x + "][" + y + "] = "
        // + toPuzzleString(used[x][y]));
      }
    }
  }

  /** Compute the used tiles visible from this position  */
  private fun calculateUsedTiles(x: Int, y: Int): IntArray {
    val c = IntArray(9)
    // horizontal
    for (i in 0..8) {
      if (i == y) continue
      val t = getTile(x, i)
      if (t != 0) c[t - 1] = t
    }
    // vertical
    for (i in 0..8) {
      if (i == x) continue
      val t = getTile(i, y)
      if (t != 0) c[t - 1] = t
    }
    // same cell block
    val startx = x / 3 * 3
    val starty = y / 3 * 3
    for (i in startx until startx + 3) {
      for (j in starty until starty + 3) {
        if (i == x && j == y) continue
        val t = getTile(i, j)
        if (t != 0) c[t - 1] = t
      }
    }
    // compress
    var nused = 0
    for (t in c) {
      if (t != 0) nused++
    }
    val c1 = IntArray(nused)
    nused = 0
    for (t in c) {
      if (t != 0) c1[nused++] = t
    }
    return c1
  }

  companion object {
    private const val TAG = "Sudoku"
    const val KEY_DIFFICULTY = "org.example.sudoku.difficulty"
    private const val PREF_PUZZLE = "puzzle"
    const val DIFFICULTY_EASY = 0
    const val DIFFICULTY_MEDIUM = 1
    const val DIFFICULTY_HARD = 2
    const val DIFFICULTY_CONTINUE = -1

    /** Convert an array into a puzzle string  */
    private fun toPuzzleString(puz: IntArray?): String {
      val buf = StringBuilder()
      for (element in puz!!) {
        buf.append(element)
      }
      return buf.toString()
    }

    /** Convert a puzzle string into an array  */
    protected fun fromPuzzleString(string: String?): IntArray {
      val puz = IntArray(string!!.length)
      for (i in puz.indices) {
        puz[i] = string[i] - '0'
      }
      return puz
    }
  }
}