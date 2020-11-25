package spm.androidworld.all.utility;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;

import androidx.core.content.ContextCompat;

public class ImageUtil {

    public static Drawable changeTintColor(Context ctx, Drawable icon, int color) {
        icon.setColorFilter(ContextCompat.getColor(ctx, color), PorterDuff.Mode.SRC_ATOP);
        return icon;
    }
}