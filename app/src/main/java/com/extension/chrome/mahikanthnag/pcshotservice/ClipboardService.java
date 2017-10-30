package com.extension.chrome.mahikanthnag.pcshotservice;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.text.ClipboardManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class ClipboardService extends FirebaseMessagingService {
    Notification notification;
    private ClipboardReceiver clipboardReceiver;
    NotificationManager notifManager;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        ClipboardManager clipboard;
        Looper.prepare();       //TODO: Judge this line's requiement
        clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        String clipboardData = remoteMessage.getData().get("copiedValue");
        clipboard.setText(clipboardData);
    }

    Notification.Builder builder;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private static final String ACTION_FOO = "com.extension.chrome.mahikanthnag.pcshotservice.action.FOO";
    private static final String ACTION_BAZ = "com.extension.chrome.mahikanthnag.pcshotservice.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.extension.chrome.mahikanthnag.pcshotservice.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.extension.chrome.mahikanthnag.pcshotservice.extra.PARAM2";

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ClipboardService.class);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.

     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, ClipboardService.class);
        context.startService(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent("com.android.ServiceStopped");
        sendBroadcast(intent);
    }
}
