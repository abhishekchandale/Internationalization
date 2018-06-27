package com.oottru.internationalization.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oottru.internationalization.MainActivity
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.model.TranslationsModel
import com.oottru.internationalization.service.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LoginActivity : AppCompatActivity() {

    private var compositeDisposable: CompositeDisposable? = null
    private var isTranslationResponseStatus: Boolean? = false
    private var isLanguageResponseStatus: Boolean? = false
    private var progress: ProgressDialog? = null
    private var gson: Gson? = null
    private var translationsList: List<TranslationsModel>? = null
    var lblEmail: TextView? = null
    var lblPassword: TextView? = null
    var edEmail: EditText? = null
    var edPassword: EditText? = null
    var btnLogin: Button? = null
    var tempIntent: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_sign_in)
        lblEmail = findViewById(R.id.mbl_lbl_username) as TextView
        lblPassword = findViewById(R.id.lbl_password) as TextView
        edEmail = findViewById(R.id.etEmail) as EditText
        edPassword = findViewById(R.id.etPassword) as EditText
        btnLogin = findViewById(R.id.btnSingIn)
        compositeDisposable = CompositeDisposable()
        gson = Gson()

        progress = ProgressDialog.show(
                this, null,
                "Preparing... ", true
        )
        btnLogin?.setOnClickListener {
            navigateToHome()
        }
        tempIntent = intent.getStringExtra(Constants.KEY_TRANSLATION_RESPONSE)
        if (tempIntent == null) {
            translationApiCall()
        } else {
            prepareUI(tempIntent!!)
        }


    }


    private val apiService by lazy {
        ApiServiceInterface.create()
    }

    fun translationApiCall() {
        compositeDisposable?.add(apiService.getTranslations(Constants.KEY_DEFAULT_LANGUAGE_CODE)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTranslationResponse, this::handleTranslationError))
    }

    private fun handleTranslationResponse(translationList: List<TranslationsModel>) {
        if (translationList.size > 0 && translationList != null) {
            isTranslationResponseStatus = true
            //saveResponse(gson?.toJson(translationList)!!)
            prepareUI(gson?.toJson(translationList)!!)
        }
    }

    private fun handleTranslationError(error: Throwable) {
        println("Error ${error.localizedMessage}")
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
    }

    fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun saveResponse(response: String) {
        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString(Constants.KEY_TRANSLATION_RESPONSE, response)
            commit()
        }
    }

    fun prepareUI(json: String) {
//        val sharedPref = this?.getPreferences(Context.MODE_PRIVATE) ?: return
//        val json = sharedPref.getString(Constants.KEY_TRANSLATION_RESPONSE, null)
        val listType = object : TypeToken<List<TranslationsModel>>() {
        }.type
        if (json == null) return else translationsList = gson?.fromJson(json, listType)
        if (translationsList != null) {
            for (index in translationsList!!) {
                if (Constants.lbl_email.toLowerCase() == index.resource_key.toLowerCase()) {
                    lblEmail?.text = index.value
                    edEmail?.hint = index.value
                }
            }
        }
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
    }


}