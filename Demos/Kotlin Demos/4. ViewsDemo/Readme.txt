15 Activities showing several Android Views and patterns (AllViewsDemo)
=======================================================================
These activities have a navigation menu (implemented in the support
library Navigation Drawer object) that starts new Activities, 
displaying some kind of View pattern.

All Activities show the Navigation Drawer that was implemented on a
base class called BaseActivity.
The MainActivity just shows that Navigation Drawer (deriving from BaseActivity).
Whenever an item on the Navigation Drawer is selected, a new Ativity is
started. If this origin activity is not the MainActivity it is specifically finished.
In this way the back button of all these activities, points always to the
MainActivity.
The items on the Navigation Drawer have icons (just to illustrate how we can add them;
they no meaning in this context).

This demo uses several support libraries for implementing some of the functionalities,
which increases the .apk size.

The minimum Android SDK supported is 8.0 and the target is Android 12.1.
The compiler, android library and tools were all from version 12.1 (API level 32).
You can change this versions in the project settings (app and module .gradle files).

The Views Activities are:

ChronoDemo - Displays the date and time on a TextView and allows for changing
them with DatePicker and TimePicker dialogs (they are different depending on
the Android version).

SlidingDemo - Displays a sliding drawer (SlidingDrawer) with a handle (image) and
an AnalogClock on it.

FlipperDemo - Displays a button and a FlipperView with 3 pannels containing
a TextView each. The pannels are shown, clicking the same button.

FlipperAutoDemo - A FlipperView with a number of pannels, containing a button
each, is first built. The FlipperView changes pannels automatically with
out and in animations (defined as resources in the anim directory).

ListViewDemo - Display a list of items in a ListView, using the standard Activity class.
All the connects must be done manually.
The selected element is shown on a TextView.
Some simplification could be achieved, but you will need a new BaseActivity
deriving from ListActivity ...

GridDemo - Displays items in a GridView allowing to select one. The last one
appears in a TextView.

ProgressDemo - Displays a ProgressBar that is updated every second by a second
thread. As the ProgressBar can only be manipulated by the UI thread, the
other thread communicates using a Handler object.

TabDemo - Displays a classical TabHost with a TabWidget and two Tabs. One contains
an AnalogClock and the other a button.

TabDynamicDemo - A TabHost containing initially only one Tab with a button.
This button, when clicked, adds another Tab containing an AnalogClock. Needs
a TabContentFactory.

TabDemoActionBar - Display the same two tabs shown in the previous demo.
This time the tabs are in the ActionBar available in a support library
(allows the same code and style AppCompat for supported Android versions).

SnackBarDemo - Displays several SnackBars (a View from the support libraries).
These SnackBar Views are like the Toast, temporarily appearing in the screen.
But they have new functionalities like the definition of actions, colorful styles
and they can be swiped away by the user. Shows also a floating button that moves
when the snack bars are active.

SwipeViewDemo - Uses a ViewPages control, that exists only on a Google support library
to allow swipe gestures to change pannels on the activity. Those pannels are instances
of fragments, built when needed. At the top there is a navigation header allowing to go
to the previous and next tabs.
At the bottom you can see a first and last buttons.

Recycler and Card List - This is a List display using the RecyclerView. The RecyclerView
comes from a support library and allow the display of collections of items according to
a list layout (different from the layout used for each item). Google claims that it is more
efficient and customizable than the ListView family, but you will need more code for those
customizations, making it a lower level (needing at least a ViewHolder and an items Adapter)
collection interface control. Also in this demo each item uses a CardView, another control
to join several views into a visual rectangle.

ShowSettings - This shows the app preferences (aka settings) on an activity, and allows
navigation to another activity (EditSettings) that allows the user to modify those
preferences. The preferences edit screen(s) come from a special resource on the /xml
sub-directory. This example illustrates dependent preferences, second screen edit and
all type of preferences. For multi-page preferences editting a support in code is required,
and illustrated here. When the user leaves the preferences editting activity the values
are persisted on an application file.