package org.feup.apm.stockservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import org.feup.apm.stockservice.common.IStockQuoteService;
import org.feup.apm.stockservice.common.IStockQuoteServiceResponse;
import org.feup.apm.stockservice.common.Person;

public class StockQuoteService extends Service {
  private NotificationManager notificationMgr;

  public class StockQuoteServiceImpl extends IStockQuoteService.Stub {
    public void getQuoteLong(String ticker, Person requester, IStockQuoteServiceResponse response) throws RemoteException {
      sendMessage("Long service call (" + android.os.Process.myTid() + ")");
      try {
        Thread.sleep(10000);
      }
      catch (InterruptedException e) {
        // Ignore
      }
      double quote = Math.random()*10 + 15.0;
      String value = String.format("%.2f", quote);
      String result = "Hello " + requester.getName() + " (" + requester.getAge() + ")! Quote for " + ticker + " is " + value;
      response.onQuoteResult(result);
    }
  }

  @Override
  public void onCreate() {
    super.onCreate();
    notificationMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    displayNotificationMessage("Service created");
    sendMessage("onCreate() called (" + android.os.Process.myTid() + ")");
  }

  @Override
  public void onDestroy() {
    // Clear all notifications from this service
    notificationMgr.cancelAll();
    sendMessage("onDestroy() called (" + android.os.Process.myTid() + ")");
    super.onDestroy();
  }

  @Override
  public IBinder onBind(Intent intent) {
    sendMessage("onBind() called (" + android.os.Process.myTid() + ")");
    return new StockQuoteServiceImpl();
  }

  private void displayNotificationMessage(String message) {
    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent("org.feup.apm.stockclientactivity"), 0);

    Notification notification = new Notification.Builder(this)
        .setSmallIcon(R.drawable.emo_im_happy)
        .setContentTitle("StockQuoteService")
        .setContentText(message)
        .setContentIntent(contentIntent)
        .build();
    notification.flags = Notification.FLAG_NO_CLEAR;
    notificationMgr.notify(R.id.app_notification_id, notification);
  }

  private void sendMessage(String message) {
    Intent intent = new Intent("android.intent.action.QUOTE_CLIENT");
    intent.putExtra("message", message);
    sendBroadcast(intent);
  }
}