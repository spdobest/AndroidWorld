package spm.androidworld.all.broadcastReceiver.ordered;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationResultReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
        final int code = getResultCode();

        if (code == Activity.RESULT_OK) {
            // app is not active
            // generate System Notification
        } else {
            // LocalBroadcastReceiver registered.
            // app is active
        }
    }
}
