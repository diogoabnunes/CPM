SimpleCalculator V0 and V1
==========================

This app implements a simple calculator.
The user can specify two values (integer or floating point) and
perform an arithmetic operation. The result is displayed.
He can also store the result in an internal memory.
Later he can recall the stored value as the first operand.

In version 0 there is no care about saving the internal state of the activity.

Try to rotate the device after storing something in the memory and recall its value.

Repeat with V1.
V1 uses the callback onSaveInstanceState() and the onCreate() bundle (the same as the onRestoreInstanceState()).