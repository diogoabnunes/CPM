package org.feup.apm.simpleservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BackgroundService extends Service {
  private NotificationManager notificationMgr;
  private ThreadGroup myThreads = new ThreadGroup("ServiceWorker");
  private MainActivity activity;

  @Override
  public void onCreate() {
    super.onCreate();

    notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    displayNotificationMessage("Local background service is running");
    activity = MainActivity.myInstance;
    if (activity != null)
      activity.addText("Service in onCreate()");
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);
    int counter = intent.getExtras().getInt("counter");
    if (activity != null)
      activity.addText("onStartCommand(): counter = " + counter + ", startId = " + startId + ", tid = " + android.os.Process.myTid());
    new Thread(myThreads, new ServiceWorker(counter), "BackgroundService").start();
    return START_NOT_STICKY;
  }

  class ServiceWorker implements Runnable {
  	private int counter = -1;

  	ServiceWorker(int counter) {
		  this.counter = counter;
	  }

  	public void run() {
  	  final int tid;
      // do background processing here...
      try {
        tid = android.os.Process.myTid();
        if (activity != null) {
          activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              activity.addText("sleeping for 10 seconds. counter = " + counter + ", tid = " + tid);
            }
          });
        }
			  Thread.sleep(10000);
        if (activity != null) {
          activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              activity.addText("... waking up (" + counter +")");
            }
          });
        }
		  }
      catch (InterruptedException e) {
        if (activity != null) {
          activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
              activity.addText("... sleep interrupted (" + counter +")");
            }
          });
        }
		  }
    }
  }

  @Override
  public void onDestroy() {
    if (activity != null)
      activity.addText("Service in onDestroy()");
    myThreads.interrupt();
    notificationMgr.cancelAll();
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  private void displayNotificationMessage(String message) {
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0);

    Notification notification = new Notification.Builder(this)
        .setSmallIcon(R.drawable.emo_im_winking)
        .setContentTitle("Local service")
        .setContentText(message)
        .setContentIntent(contentIntent)
        .build();
    notification.flags = Notification.FLAG_NO_CLEAR;
    notificationMgr.notify(0, notification);
  }
}