PlanetsFragments
================
An Android app that shows some information about the solar system planets.
That information, and also the planets' list, is displayed inside fragments.
The app behaves differently in portrait and landscape.
In portrait it shows the planets' list in the main activity and creates
another activity to show the details.
In landscape there is only a main activity that includes both the list and
the details (details include a picture and some text).
The details fragment is dynamic and is replaced by another instance, whenever
the user selects a different planet.

The app play some music at the App level (not by the activities).
Android has a MusicPlayer class for playing sound files.
In order to mantain continuity when activites change, we need to use the
singleton app Application object, that remains in memory while the process
is there. The Application object is not destroyed when Activities are, and it
can also be called back whenever any Activity changes state.

See also the About Activity, that displays with Dialog style, and is invoked by
the menu entry on the main Activity Action Bar.