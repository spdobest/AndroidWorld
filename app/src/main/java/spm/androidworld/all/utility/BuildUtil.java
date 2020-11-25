package spm.androidworld.all.utility;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;

public class BuildUtil {

    public static boolean isMarshemallo() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    @SuppressLint("HardwareIds")
    public static String getDeviceId(Context ctx) {
        return Settings.Secure.getString(ctx.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
}