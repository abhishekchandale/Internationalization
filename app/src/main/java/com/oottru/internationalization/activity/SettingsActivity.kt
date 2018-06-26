package com.oottru.internationalization.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.preference.PreferenceFragment
import com.oottru.internationalization.R


class SettingsActivity : AppCompatPreferenceActivity() {

    private val TAG = SettingsActivity::class.java.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        // load settings fragment
        fragmentManager.beginTransaction().replace(android.R.id.content, MainPreferenceFragment()).commit()
    }
}

class MainPreferenceFragment : PreferenceFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.pref_main)
    }
}
