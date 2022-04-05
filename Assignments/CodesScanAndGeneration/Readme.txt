Demos using the Google ZXing library with apps to read and generate optical codes.
There are two Android applications:

ScanCodes
=========
Scans and show the messages read from QRcodes and barcodes.
It uses the scanning activity of another app through an implicit intent,
calling startActivityForResult().
If the other app is not installed on the device it asks for installation using
the Google Play Store (apps in play store have a special URL: market://...)

ZX Code Generator
=================
Generates a  Bar code to represent a stantadrd number or a QR code to represent a
String message, transforms both into a Bitmap, and displays it in the screen.
The Bar code or the QR code are generated as an array of bytes that is transformed
into the 2D bitmap code.
Experiment with different values or message sizes, from a few tens of characters
through near 1000 characters. Observe the QR code shape change with the message size.
QR codes contain some redundancy, allowing some error correction if optical
acquisition errors occur.