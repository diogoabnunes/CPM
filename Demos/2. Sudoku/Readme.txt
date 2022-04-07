Sudoku Game
===========
(written initially in Java and converted automatically to Kotlin,
 so some parts can look strange ...)


This demo uses several Android features in an application with several activities.

The opening screen shows a button made menu, that adapts to orientation (portrait and
landscape) (Sudoku.kt).

It has also an Options Menu giving access to some preferences (Prefs.kt).

There is also an Activity that looks like a Dialog box (About.ky), because it uses the
dialog theme specified in the manifest file.

The game is played in an Activity (Game.kt) that shows a graphic View (PuzzleView.kt)
where lines, colors and text (the numbers) are drawn directly. There is also a button
made keypad (Keypad.kt) for facilitating the numbers' entering in hint mode.

The game state is also persisted, allowing its continuation at a later time.

Through the preferences, hints can be provided (dark green squares have only one possibility
and light green ones have two) and also some music can be played (Music.kt) at the opening
screen and game screen. The music files are embedded raw resources (inside res\raw).