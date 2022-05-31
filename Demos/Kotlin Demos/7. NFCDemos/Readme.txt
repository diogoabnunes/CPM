NFC Send and Receive
====================

This app allows to send and receive text messages using two phones with NFC and the same
app installed.
The messages are transmitted using the NFC P2P mode (Android beam).
Both NFC and Android Beam should be enabled in settings, before the app could be used.
After Android API 28, it is possible that Android Beam is not available even
with NFC devices, but many still support this mode.



The next pair of apps demonstrate the ability of Android to simulate Wireless SmartCards
in software (using the Card Emulation mode of NFC).
SmartCards contain applets that can accept commands (in byte APDU format) generating
replies if recognized (in the applet softeare). These commands and results are transmitted
in a serial line (in the standard smartcard connector) or using a NFC channel.
Each applet has an associated AID (a 5 to 13 byte number) and the first operation
for using it should be a selectAID command sent to the smartcard.
A smartcard can have installed several applets.

NFC Reader
==========

Implements a reader of NFC Smartcards using IsoDep protocol and card (as the one emulated
in the next app). After detecting the presence of the card (NFC channel established)
it emits the selectAID command (using the same AID as the one associated with the applet
emulated in the next app), and reads the reply. If the reply is valid, it shows the
payload that contains a card number.

NFCCardEmulator
===============

Android allows the simulation of a smartcard applet in a special service that will work
with the NFC controller. That service should derive from HostApduService. The associated
AID is defined in a XML resource (in the res/xml directory).
In this simple example the applet, when selected, returns imediatelly a number
(representing a card number, that can be changed and persisted in the Android
application Activity).
The simple installation of the application (with the phone NFC enabled), makes the
device respond to NFC readers that select the emulated applet, as if it was a physical
SmartCard.


The two previous apps should be installed in different phones and require API 19 or later
and support from the NFC hardware (not all phones with NFC are capable of working in
Card Emulation mode).
