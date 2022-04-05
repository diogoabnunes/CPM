DialogFragmentDemo
==================

In this app there is an intermediate class derived from Fragment.
The activity builds and displays the dialog using the Fragment.
Fragments are retained when the device is rotated (if build through a factory, not the constructor).
All the rest is similar to the previous app.

Try to rotate the screen when the dialog is already displayed.
The main activity derives from AppCompatActivity that derives from FragmentActivity, before deriving from Activity.
The intermediary class, that wraps the Dialog, derives from AppCompatDialogFragment.