package com.oottru.internationalization

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import com.google.gson.Gson
import com.oottru.internationalization.R.id.*
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.Util.Prefs
import com.oottru.internationalization.activity.SettingsActivity
import com.oottru.internationalization.fragment.ChangeLanguageFragment
import com.oottru.internationalization.fragment.ProfileFragment
import com.oottru.internationalization.fragment.ProjectDetailFragment
import com.oottru.internationalization.fragment.ProjectListFragment
import com.oottru.internationalization.model.TranslationApiResponse
import com.oottru.internationalization.model.TranslationsModel
import com.oottru.internationalization.receiver.ConnectivityReceiver
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, ChangeLanguageFragment.LanguageChangeListener, ConnectivityReceiver.ConnectivityReceiverListener {

    private var mSnackBar: Snackbar? = null
    override fun updateLanguage(l: String) {
        var prefs: Prefs = Prefs(this)
        changeMenuText(prefs?.transaltion)
    }


    private var tempIntent: String? = null
    private var gson: Gson? = null
    private var translationModel: List<TranslationsModel>? = null
    private var translationApiResponse: TranslationApiResponse? = null
    private var prefs: Prefs? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerReceiver(ConnectivityReceiver(),
                IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        setContentView(R.layout.activity_main)
        gson = Gson()
        tempIntent = intent?.getStringExtra(Constants.KEY_TRANSLATION_RESPONSE)
        setSupportActionBar(toolbar)
        initNavigation()
        prefs = Prefs(this)
        changeMenuText(prefs?.transaltion!!)
//        if (tempIntent != null)
//            changeMenuText(tempIntent!!)
        navigateTo(ProjectListFragment.newInstance())
    }

    private fun showMessage(isConnected: Boolean) {

        if (!isConnected) {

            val messageToUser = "You are offline now." //TODO

            mSnackBar = Snackbar.make(findViewById(R.id.drawer_layout), messageToUser, Snackbar.LENGTH_LONG) //Assume "rootLayout" as the root layout of every activity.
            mSnackBar?.duration = Snackbar.LENGTH_INDEFINITE
            mSnackBar?.show()
        } else {
            mSnackBar?.dismiss()
        }


    }

    private fun initNavigation() {
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (Build.VERSION.SDK_INT > 11) {
            invalidateOptionsMenu();
            menu?.findItem(R.id.mbl_lblsignout)?.setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        if (id == R.id.action_settings) {
            // launch settings activity
            startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateTo(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.contentFrame, fragment).commit()
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        println("onNavigation changed call")
        when (item.itemId) {
            R.id.mbl_lbl_home -> {
                // Handle the camera action
                //    toast("Click Camera")


                /*
                //from google sample
                val fragmentOne = supportFragmentManager.findFragmentById(R.id.contentFrame)
                        as ProjectListFragment? ?: ProjectListFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }
                */

                //my way
                /*
                supportFragmentManager.beginTransaction()
                        .replace(R.id.contentFrame, ProjectListFragment.newInstance()).commit()
                        */
                navigateTo(ProjectListFragment.newInstance())

            }
            R.id.mbl_lbl_machinesetup -> {
                navigateTo(ProjectDetailFragment.newInstance())

            }
            R.id.mbl_lbl_contactinfo -> {

            }
            R.id.mbl_lblsignout -> {
                //  navigateTo(SignInFragment.newInstance())
            }
            R.id.mbl_lbl_profile -> {
                navigateTo(ProfileFragment.newInstance())
            }
            R.id.mbl_lbl_language -> {
                navigateTo(ChangeLanguageFragment.newInstance())
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun changeMenuText(translation: String) {
        ///   val listType = object : TypeToken<List<TranslationsModel>>() {}.type
        if (translation == null) {
            return
        } else {
            translationApiResponse = gson?.fromJson(translation, TranslationApiResponse::class.java)
            translationModel = translationApiResponse?.Translation_Masters
        }
        val navigationView = findViewById(R.id.nav_view) as NavigationView
        var menu: Menu = navigationView.getMenu()
        if (translationModel != null) {
            for (index in translationModel!!) {
                if (Constants.MBL_LBL_MACHINESETUP == index.resource_key)
                    menu.findItem(R.id.mbl_lbl_machinesetup).title = index.value
                if (Constants.MBL_LBL_HOME == index.resource_key)
                    menu.findItem(R.id.mbl_lbl_home).title = index.value
                if (Constants.MBL_LBL_CONTACTINFO == index.resource_key)
                    menu.findItem(R.id.mbl_lbl_contactinfo).title = index.value
                if (Constants.MBL_LBL_LANGUAGE == index.resource_key)
                    menu.findItem(R.id.mbl_lbl_language).title = index.value
                if (Constants.MBL_LBLSIGNOUT == index.resource_key)
                    menu.findItem(R.id.mbl_lblsignout).title = index.value
                if (Constants.MBL_LBL_PROFILE == index.resource_key)
                    menu.findItem(R.id.mbl_lbl_profile).title = index.value
            }

        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish()
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onResume() {
        super.onResume()

        ConnectivityReceiver.connectivityReceiverListener = this
    }

    /**
     * Callback will be called when there is change
     */
    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showMessage(isConnected)
    }
}
