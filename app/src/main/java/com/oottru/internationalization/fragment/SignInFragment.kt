package com.oottru.internationalization.fragment

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oottru.internationalization.MainActivity
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.Util.Prefs
import com.oottru.internationalization.model.TranslationsModel
import com.oottru.internationalization.service.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_sign_in.*


class SignInFragment : Fragment() {

    private var compositeDisposable: CompositeDisposable? = null
    private var isTranslationResponseStatus: Boolean? = false
    private var progress: ProgressDialog? = null
    private var gson: Gson? = null
    private var translationsList: List<TranslationsModel>? = null
    var lblEmail: TextView? = null
    var lblPassword: TextView? = null
    var lblLoginHead: TextView? = null
    var edEmail: EditText? = null
    var edPassword: EditText? = null
    var btnLogin: Button? = null
    var tempIntent: String? = null
    var mView: View? = null
    var prefs: Prefs? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return mView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        tempIntent = arguments?.getString(Constants.KEY_TRANSLATION_RESPONSE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lblEmail = mView?.findViewById(R.id.mbl_lbl_username) as TextView
        lblPassword = mView?.findViewById(R.id.mbl_lbl_password) as TextView
        lblLoginHead = mView?.findViewById(R.id.mbl_login_head) as TextView
        edEmail = mView?.findViewById(R.id.etEmail) as EditText
        edPassword = mView?.findViewById(R.id.etPassword) as EditText
        btnLogin = mView?.findViewById(R.id.mbl_btn_login) as Button
        prefs = Prefs(this.activity!!)
        if (tempIntent == null) {
            translationApiCall()
        } else {
            prepareUI(tempIntent!!)
        }
        btnLogin?.setOnClickListener {
            prefs?.isLogin = true
            navigateToActivity(tempIntent!!)
        }
    }


    companion object {
        fun newInstance() = SignInFragment()
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


    fun prepareUI(json: String) {
        gson = Gson()
        val listType = object : TypeToken<List<TranslationsModel>>() {}.type
        if (json == null) {
            return
        } else {
            translationsList = gson?.fromJson(json, listType)
        }
        if (translationsList != null) {
            for (index in translationsList!!) {

                if (Constants.PROJECT_SINGIN_MESSAGE.toLowerCase() == index.resource_key.toLowerCase()) {
                    signin_msg?.text = index.value

                }
                if (Constants.LBL_USERNAME.toLowerCase() == index.resource_key.toLowerCase()) {
                    lblEmail?.text = index.value
                    edEmail?.hint = index.value
                }
                if (Constants.LBL_PASSWORD.toLowerCase() == index.resource_key.toLowerCase()) {
                    lblPassword?.text = index.value
                    edPassword?.hint = index.value
                }
                if (Constants.BTN_SIGNIN.toLowerCase() == index.resource_key.toLowerCase()) {
                    mbl_btn_login.setText(index.value)

                }
                if (Constants.MBL_LOGIN_HEAD.toLowerCase() == index.resource_key.toLowerCase()) {
                    lblLoginHead?.text = index.value

                }
            }
        }
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
    }

    fun navigateToActivity(translation: String) {
        val intent = Intent(activity, MainActivity::class.java)
        intent.putExtra(Constants.KEY_TRANSLATION_RESPONSE, translation)
        startActivity(intent)
    }
}