Demos with Brodacast Receivers
==============================

StandAloneReceiver2 and SendBroadcastReceiver

StandAloneReceiver2 contains a broadcast receiver that creates a notification when it executes.
Prior to Android API level 12 all broadcast receivers could be invoked even if their processes have never been started. After API level 12, broadcast receivers associated to custom intents (or even certain system intents), require that the process where the BR belongs to, has executed at least once (passing the process from a STOPPED/INSTALLED state to a STARTED state). In order to garantee that, the app that contains a broadcast receiver can contain another Activity that will make the broadcast receiver operational (after that activity had runned at least once).
In standalone receivers (apps that contain only a broadcast receiver without activities), it is also possible to specify (with an additional flag in the intent) that never STARTED processes should also be considered, which SendBroadcastReceiver does when it sends the broadcast.
SendBroadcastReceiver generates a broadcast intent to the previous broadcast receiver using a menu item. The broadcast receiver creates a notification associated with a pending intent (with action ACTION_DIAL) starting the dial application when clicked.

After API 26, calling Broadcast receivers between different applications (targetting an API >= 26) is only possible (except for OS broadcasts) if an explicit ComponentName is created in the intent (besides the ACTION). Scheduled jobs partially replaced that functionality.

Note: Android Studio does not install directly in emulators or devices apps without Activities. You should generate explicitly the APK using Build -> Build Bundle(s) / APK(s) -> Build APK(s) in Android Studio. You will find the APK file in the project directory inside: <project_dir>\app\build\outputs\apk.
Use the adb SDK command-line tool to install it on an emulator or connected device: adb install <apk_file>
