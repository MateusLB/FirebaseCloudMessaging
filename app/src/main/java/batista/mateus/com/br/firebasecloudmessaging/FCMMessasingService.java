package batista.mateus.com.br.firebasecloudmessaging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class FCMMessasingService extends FirebaseMessagingService{

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        super.onMessageReceived(remoteMessage);

            if (remoteMessage.getData().size() > 0) {

                String messageString = remoteMessage.getData().get("message");

                Intent it = new Intent(this, MainActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), it, 0);
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                int ico_notification = R.drawable.starkicon;
                int color = ContextCompat.getColor(this, R.color.starkColor);


                NotificationManager mNotificationManager = (NotificationManager)
                        this.getSystemService(Context.NOTIFICATION_SERVICE);

                String CHANNEL_ID = "FCM_channel_01";
                CharSequence name = "Channel fCM";
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    int importance = NotificationManager.IMPORTANCE_DEFAULT;
                    NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                    mNotificationManager.createNotificationChannel(mChannel);
                }


                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(this,CHANNEL_ID)
                                .setSmallIcon(ico_notification)
                                .setContentTitle(getString(R.string.app_name))
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText(messageString))
                                .setSound(soundUri)
                                .setColor(color)
                                .setAutoCancel(true)
                                .setVibrate(new long[]{1000, 1000})
                                .setContentText(messageString);

                mBuilder.setContentIntent(contentIntent);

                Notification notification = mBuilder.build();
                notification.ledARGB = color;
                notification.flags = Notification.FLAG_SHOW_LIGHTS|Notification.FLAG_AUTO_CANCEL;
                notification.ledOnMS = 1000;
                notification.ledOffMS = 1000;

                mNotificationManager.notify(0, notification);


            }
    }
}
