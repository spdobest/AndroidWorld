/*
 * Copyright © 2016, Craftsvilla.com
 *  Written under contract by Robosoft Technologies Pvt. Ltd.
 */
package com.heyyy.main.utility

import android.graphics.Paint
import android.text.TextUtils
import android.util.Patterns
import android.widget.TextView
import com.heyyy.main.utility.AppConstant.Companion.EMPTY_TEXT
import java.io.UnsupportedEncodingException
import java.net.URLEncoder
import java.util.*

/**
 * Created by Sibaprasad Mohanty on 2020-01-13.
 * SPM Limited
 * sp.dobest@gmail.com
 */
object StringUtil {
    private const val DEFAULT_PARAMS_ENCODING = "UTF-8"
    /*public static String getRequestBody( Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return null;
        }
    }*/
    fun getEncodedText(param: String?): String {
        try {
            if (!TextUtils.isEmpty(param)) return URLEncoder.encode(
                param,
                DEFAULT_PARAMS_ENCODING
            )
        } catch (e: UnsupportedEncodingException) {
            return EMPTY_TEXT
        }
        return EMPTY_TEXT
    }

    fun setStrikeTextViewWithString(view: TextView, text: String?) {
        view.text = text
        view.paintFlags = view.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
    }

    fun getCleverTapAnalyticsMap(
        key: String,
        value: String
    ): Map<String, Any> {
        val map =
            HashMap<String, Any>()
        map[key] = value
        return map
    }

    fun isEmailIdValid(emailId: String?): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(emailId.toString()).matches()
    }
}