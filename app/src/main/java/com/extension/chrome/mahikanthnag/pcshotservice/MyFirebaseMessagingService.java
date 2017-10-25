package com.extension.chrome.mahikanthnag.pcshotservice;

/**
 * Created by Mahikanth Nag on 7/28/2017.
 */

import android.app.Notification;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Ravi Tamada on 08/08/16.
 * www.androidhive.info
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();

    private Notification.Builder builder;
    private Notification notification;
    private NotificationManager notifManager;
//    private NotificationUtils notificationUtils;

    public static Map notificationData;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.e(TAG, "From: " + remoteMessage.getFrom());
        Map<String, String> map;
        map = remoteMessage.getData();
        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.e(TAG, "Notification Body: " + remoteMessage.getNotification().getBody());
//            handleNotification(remoteMessage.getNotification().getBody());
        }

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {

            notificationData = remoteMessage.getData();
            Log.e(TAG, "Data Payload: " + remoteMessage.getData().toString());
            notifManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            builder = new Notification.Builder(this);
            builder.setSmallIcon(R.drawable.me).setContentTitle(remoteMessage.getData().get("title")).setContentText(remoteMessage.getData().get("body"));
            notification = builder.getNotification();
            notifManager.notify(R.drawable.me, notification);

            try {
                JSONObject json = new JSONObject(remoteMessage.getData().toString());
//                handleDataMessage(json);
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
            }

            SharedPreferences pref = getSharedPreferences("DataFetchRequired", 0); // 0 - for private mode
            SharedPreferences.Editor editor = pref.edit();
            editor.putBoolean("shouldUpdate", true);
            editor.putString("notification_title", remoteMessage.getData().get("title"));
            editor.putString("notification_body", remoteMessage.getData().get("body"));
            editor.putString("notification_type", remoteMessage.getData().get("notification_type"));
            editor.commit();
        }

//        if(remoteMessage.getData().get("notification_type").equals("promote"))
//        {
//            HomeActivity.workFlowDataStatic.currentRoundID ++;
////                    Integer.parseInt(remoteMessage.getData().get("to"));
//        }
        /*
        else if(remoteMessage.getData().get("notification_type").equals("assigned"))
        {
            HomeActivity.workFlowDataStatic.stages.get(HomeActivity.workFlowDataStatic.currentRoundID).setVenue(map.get("venue"));
            HomeActivity.workFlowDataStatic.stages.get(HomeActivity.workFlowDataStatic.currentRoundID).setTime(map.get("time"));
        }*/
    }


//    private void handleNotification(String message) {
//        if (!NotificationUtils.isAppIsInBackground(getApplicationContext()))
//        {
//            // app is in foreground, broadcast the push message
//            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
//            pushNotification.putExtra("message", message);
////            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
//
//            // play notification sound
//            NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
//            notificationUtils.playNotificationSound();
//        }
//        else{
//            // If the app is in background, firebase itself handles the notification
//        }
//    }

//    private void handleDataMessage(JSONObject json) {
//        Log.e(TAG, "push json: " + json.toString());
//
//        try {
//            JSONObject data = json.getJSONObject("data");
//
//            String title = data.getString("title");
//            String message = data.getString("message");
//            boolean isBackground = data.getBoolean("is_background");
//            String imageUrl = data.getString("image");
//            String timestamp = data.getString("timestamp");
//            JSONObject payload = data.getJSONObject("payload");
//
//            Log.e(TAG, "title: " + title);
//            Log.e(TAG, "message: " + message);
//            Log.e(TAG, "isBackground: " + isBackground);
//            Log.e(TAG, "payload: " + payload.toString());
//            Log.e(TAG, "imageUrl: " + imageUrl);
//            Log.e(TAG, "timestamp: " + timestamp);
//

//            if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
//                // app is in foreground, broadcast the push message
//                Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
//                pushNotification.putExtra("message", message);
////                LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);
//
//                // play notification sound
//                NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
//                notificationUtils.playNotificationSound();
//            }
//            else {
//                // app is in background, show the notification in notification tray
//                Intent resultIntent = new Intent(getApplicationContext(), NotificationsActivity.class);
//                resultIntent.putExtra("message", message);
//
//                // check for image attachment
//                if (TextUtils.isEmpty(imageUrl)) {
//                    showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
//                } else {
//                    // image is present, show notification with image
//                    showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
//                }
//            }
//        } catch (JSONException e) {
//            Log.e(TAG, "Json Exception: " + e.getMessage());
//        } catch (Exception e) {
//            Log.e(TAG, "Exception: " + e.getMessage());
//        }
//    }

//    /**
//     * Showing notification with text only
//     */
//    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
//        notificationUtils = new NotificationUtils(context);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        notificationUtils.showNotificationMessage(title, message, timeStamp, intent);
//    }

    /**
     * Showing notification with text and image
     */
//    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
//        notificationUtils = new NotificationUtils(context);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
//    }
}
