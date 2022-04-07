package org.example.sudoku

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Sudoku : AppCompatActivity(), View.OnClickListener {
  /** Called when the activity is first created.  */
  public override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // Set up click listeners for all the buttons
    val continueButton = findViewById<View>(R.id.continue_button)
    continueButton.setOnClickListener(this)
    val newButton = findViewById<View>(R.id.new_button)
    newButton.setOnClickListener(this)
    val aboutButton = findViewById<View>(R.id.about_button)
    aboutButton.setOnClickListener(this)
    val exitButton = findViewById<View>(R.id.exit_button)
    exitButton.setOnClickListener(this)
  }

  override fun onResume() {
    super.onResume()
    Music.play(this, R.raw.main)
  }

  override fun onPause() {
    super.onPause()
    Music.stop(this)
  }

  override fun onClick(v: View) {
    when (v.id) {
      R.id.continue_button -> startGame(Game.DIFFICULTY_CONTINUE)
      R.id.about_button -> {
        val i = Intent(this, About::class.java)
        startActivity(i)
      }
      R.id.new_button -> openNewGameDialog()
      R.id.exit_button -> finish()
    }
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    super.onCreateOptionsMenu(menu)
    val inflater = menuInflater
    inflater.inflate(R.menu.menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.settings -> {
        startActivity(Intent(this, Prefs::class.java))
        return true
      }
    }
    return false
  }

  /** Ask the user what difficulty level they want  */
  private fun openNewGameDialog() {
    AlertDialog.Builder(this)
      .setTitle(R.string.new_game_title)
      .setItems(R.array.difficulty,
        DialogInterface.OnClickListener { dialoginterface, i -> startGame(i) })
      .show()
  }

  /** Start a new game with the given difficulty level  */
  private fun startGame(i: Int) {
    val intent = Intent(this@Sudoku, Game::class.java)
    intent.putExtra(Game.KEY_DIFFICULTY, i)
    startActivity(intent)
  }

  companion object {
    private const val TAG = "Sudoku"
  }
}