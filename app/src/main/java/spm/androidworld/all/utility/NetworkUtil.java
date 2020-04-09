package spm.androidworld.all.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Sibaprasad Mohanty on 2020-01-13.
 * SPM Limited
 * sp.dobest@gmail.com
 */

public class NetworkUtil {

    public static boolean isAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}