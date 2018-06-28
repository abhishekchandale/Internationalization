package com.oottru.internationalization

import android.app.Application
import com.oottru.internationalization.Util.Prefs

class MyApplication : Application() {

    var prefs: Prefs? = null
    override fun onCreate() {
        super.onCreate()
        //for testing purpose
        prefs = Prefs(this)
        prefs?.isLogin = false
    }

    companion object {
        private val mInstance: MyApplication = MyApplication()

        @Synchronized
        fun getInstance(): MyApplication {
            return mInstance
        }
    }


}