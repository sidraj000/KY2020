package org.kashiyatra.ky20.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.kashiyatra.ky20.R;
import org.kashiyatra.ky20.SplashActivity;

import java.net.URL;

public class MyFirebaseMessagingService extends FirebaseMessagingService{

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        FirebaseMessaging.getInstance().subscribeToTopic("General");
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        if(remoteMessage.getData()!=null)
            sendNotification(remoteMessage);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void sendNotification(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        String imageUrl = remoteMessage.getData().get("image_url");
        String subText = remoteMessage.getData().get("sub_text");
        String title = remoteMessage.getData().get("title");
        String body = remoteMessage.getData().get("body");
        String link = remoteMessage.getData().get("link");
        Log.d(TAG, "Message Notification Title: " + title);
        Log.d(TAG, "Message Notification ImageUrl: " + imageUrl);
        Log.d(TAG, "Message Notification Body: " + body);
        Log.d(TAG, "Message Notification SubText: " + subText);
        Log.d(TAG, "Message Notification Link: " + link);


        Intent intent = new Intent(this, SplashActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* request code */, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        final String CHANNEL_ID = "ky_general";

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setColor(getColor(R.color.colorPrimaryDark))
                .setContentText(body)
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "KY General";
            String description = "Channel for KY Notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        if (imageUrl != null) {
            try {
                URL url = new URL(imageUrl);
                Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(image));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (subText != null) {
            notificationBuilder.setSubText(subText);
        }

        Intent visitIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));

        PendingIntent pendingIntentLink = PendingIntent.getActivity(this, 101, visitIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        notificationBuilder.addAction(R.drawable.ic_submit, "More", pendingIntentLink);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }
}
