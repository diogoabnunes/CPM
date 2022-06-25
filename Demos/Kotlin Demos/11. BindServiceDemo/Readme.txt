Demo with Android Service (bindable)
====================================================

StockQuoteService and StockQuoteClient

StockQuoteService contains only a service component that can be invoked through RPC style calls (binding). It defines an interface that can be obtained by calling bindService() from other activities. The interface methods are called using an inter-process mechanism and by marshalling the parameters and results between the processes (all types transferred should be Parcelable and implemented that way). The interface and its methods must be described in an interface description language called AIDL (Android Interface Description Language).
StockQuoteClient is an application containing an activity that is the client of the previous service, binding and invoking its method. In this case (it's not mandatory) the call is asynchronous (oneway AIDL defined interfaces), returning immediately, with the client defining also an interface callback through an AIDL file, and letting the remote call, when complete, call this callback in the client. You can see in the client, the evolution of the calls in a message log, where messages from the service are also transmitted to the client using a broadcast receiver.
Note the threads involved observing their thread IDs.