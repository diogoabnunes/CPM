package org.feup.apm.standalonereceiver2;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class StandAloneReceiver extends BroadcastReceiver {
  @Override
  public void onReceive(Context ctx, Intent intent) {
    String message = intent.getStringExtra("message");
    sendNotification(ctx, message);
  }

  @TargetApi(Build.VERSION_CODES.O)
  private void sendNotification(Context ctx, String message) {
    //Get the notification manager
    NotificationManager nm = (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
    NotificationChannel nc = new NotificationChannel("brchan", "my_br_channel", NotificationManager.IMPORTANCE_HIGH);
    nm.createNotificationChannel(nc);

    Intent intent = new Intent(Intent.ACTION_DIAL);
    PendingIntent pi = PendingIntent.getActivity(ctx, 0, intent, PendingIntent.FLAG_IMMUTABLE);

    //Create Notification Object
    Notification notification = new Notification.Builder(ctx)
        .setSmallIcon(R.drawable.robot)
        .setContentIntent(pi)
        .setContentTitle("From Me")
        .setContentText(message)
        .setChannelId("brchan")
        .build();
    //Send notification
    nm.notify(1, notification);
  }
}
