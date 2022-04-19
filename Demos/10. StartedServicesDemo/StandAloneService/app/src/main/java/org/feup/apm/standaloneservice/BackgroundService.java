package org.feup.apm.standaloneservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BackgroundService extends Service {
  private NotificationManager notificationMgr;
  private ThreadGroup myThreads = new ThreadGroup("ServiceWorker");

  @Override
  public void onCreate() {
    super.onCreate();
    notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    displayNotificationMessage("Remote Service is running");
    sendMessage("Service in onCreate()");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);
    int counter = intent.getExtras().getInt("counter");
    sendMessage("onStartCommand(): counter = " + counter + ", startId = " + startId + ", tid = " + android.os.Process.myTid());
    new Thread(myThreads, new ServiceWorker(counter), "BackgroundService").start();
    return START_NOT_STICKY;
  }

  class ServiceWorker implements Runnable {
  	private int counter = -1;

  	public ServiceWorker(int counter) {
		  this.counter = counter;
	  }

  	public void run() {
  	  int tid = android.os.Process.myTid();
      // do background processing here...
      try {
        sendMessage("sleeping for 10 seconds. counter = " + counter + ", tid = " + tid);
        Thread.sleep(10000);
		  sendMessage("... waking up (" + counter +")");
		  } catch (InterruptedException e) {
        sendMessage("... sleep interrupted (" + counter +")");
		  }
    }
  }

  @Override
  public void onDestroy() {
    sendMessage("Service in onDestroy()");
    myThreads.interrupt();
    notificationMgr.cancelAll();
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  private void displayNotificationMessage(String message) {
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent("org.feup.apm.intents.serviceactivity"), 0);

    Notification notification = new Notification.Builder(this)
                                    .setSmallIcon(R.drawable.emo_im_winking)
                                    .setContentTitle("Remote Service")
                                    .setContentText(message)
                                    .setContentIntent(contentIntent)
                                    .build();
    notification.flags = Notification.FLAG_NO_CLEAR;
    notificationMgr.notify(0, notification);
  }

  private void sendMessage(String message) {
    Intent intent = new Intent("android.intent.action.RCV_MESS");
    intent.putExtra("message", message);
    sendBroadcast(intent);
  }
}
