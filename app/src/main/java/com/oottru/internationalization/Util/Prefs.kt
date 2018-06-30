package com.oottru.internationalization.Util

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color

class Prefs(context: Context) {
    val PREFS_FILENAME = "com.oottru.internationalization.prefs"
    val BACKGROUND_COLOR = "background_color"
    var ISLOGIN = "islogin"
    var LANGUAGE = "language"
    var TRANSALATION = "translation"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, 0);

    var bgColor: Int
        get() = prefs.getInt(BACKGROUND_COLOR, Color.BLACK)
        set(value) = prefs.edit().putInt(BACKGROUND_COLOR, value).apply()

    var isLogin: Boolean
        get() = prefs.getBoolean(ISLOGIN, false)
        set(value) = prefs.edit().putBoolean(ISLOGIN, value).apply()
    var language: String
        get() = prefs.getString(LANGUAGE, "EN")
        set(value) = prefs.edit().putString(LANGUAGE, value).apply()
    var transaltion: String
        get() = prefs.getString(TRANSALATION, "")
        set(value) = prefs.edit().putString(TRANSALATION, value).apply()
}