package spm.androidworld.all.utility

import android.util.Log
import spm.androidworld.all.BuildConfig

/**
 * Created by Sibaprasad Mohanty on 2020-01-13.
 * SPM Limited
 */
object LogUtil {
    private const val IS_DEBUG = BuildConfig.isDebugable
    /**
     * To Show the Logs using tag and msg
     *
     * @param tag
     * @param message
     */
    fun showLog(tag: String?, message: String) {
        if (IS_DEBUG) {
            val maxLogSize = 1024
            if (message.length > 1024) {
                for (i in 0..message.length / maxLogSize) {
                    val start = i * maxLogSize
                    var end = (i + 1) * maxLogSize
                    end = if (end > message.length) message.length else end
                    Log.i(tag, message.substring(start, end))
                }
            } else {
                Log.i(tag, message)
            }
        }
    }
}