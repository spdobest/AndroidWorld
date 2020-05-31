package spm.androidworld.all.broadcastReceiver.ordered;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class LocalBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent i) {
        // Tell the result receiver to CANCEL some specific action.
        // eg. do not display System Notification
        setResultCode(Activity.RESULT_CANCELED);
    }
}