package com.extension.chrome.mahikanthnag.pcshotservice;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.text.ClipboardManager;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import static android.content.Context.CLIPBOARD_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class ClipboardReceiver extends BroadcastReceiver {
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    Notification notification;
    private ClipboardReceiver clipboardReceiver;
    NotificationManager notifManager;
    Notification.Builder builder;
    Context context;
    ClipboardManager clipboard;
    @Override
    public void onReceive(final Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("BReceiver called", "true");
        this.context = context;

        clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);

//        notifManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
//        builder = new Notification.Builder(context);
//        builder.setSmallIcon(R.drawable.me).setContentTitle("ERROR!").setContentText("Connect To Internet To Complete This Action");
//        notification = builder.getNotification();
//        notifManager.notify(R.drawable.me, notification);
//        throw new UnsupportedOperationException("Not yet implemented");
    }
    public boolean testConnection(Context context) {

        try {
            ConnectivityManager connMgr = (ConnectivityManager)
                    context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                return true;
            } else {
                // display error
                return false;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
