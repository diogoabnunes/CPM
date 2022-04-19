Demos with Android Services (startable)
====================================================

SimpleServiceDemo and StandAloneService

SimpleServiceDemo has an Activity that invokes a service through an intent and the starService() method. This application contains also a local service component that can be invoked that way. The Activity can also invoke another service in another application using also startService() (and an implicit intent).
The local service or the remote one are selected by a radio button.
StandAloneService contains the remote service called by the activity in SimpleServiceDemo.
See the SimpleServiceDemo activity messages generated in the code of the client and the service.
Only for the purpose of transmitting such status messages from the remote service, the StandAloneService sends brodacasts to a broadcast receiver registered dynamically on the client (SimpleServiceDemo).

