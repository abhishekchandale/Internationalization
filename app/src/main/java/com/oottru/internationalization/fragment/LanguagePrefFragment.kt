package com.oottru.internationalization.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.google.gson.Gson
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.activity.LoginActivity
import com.oottru.internationalization.fragment.adapter.LanguagePrefAdapter
import com.oottru.internationalization.model.LanguageModel
import com.oottru.internationalization.model.TranslationsModel
import com.oottru.internationalization.service.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class LanguagePrefFragment : Fragment() {

    private var mView: View? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var progress: ProgressDialog? = null
    private var gson: Gson? = null
    private var language: ArrayList<LanguageModel>? = null
    private var recyclerView: RecyclerView? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_language, container, false)
        return mView
    }

    private val apiService by lazy {
        ApiServiceInterface.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = mView?.findViewById(R.id.language_recycler) as RecyclerView
        gson = Gson()
        compositeDisposable = CompositeDisposable()
        languageApiCall()
    }


    companion object {
        fun newInstance() = LanguagePrefFragment()
    }

    fun languageApiCall() {
        progress = ProgressDialog.show(
                activity!!, null,
                "Preparing... ", true
        )
        compositeDisposable?.add(apiService.getLanguagePreferences()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleLanguageResponse, this::handleLanguageError))
    }

    fun handleLanguageResponse(languageList: List<LanguageModel>) {
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
        if (languageList.size > 0 && languageList != null) {
            language = ArrayList(languageList)
            val adapter = LanguagePrefAdapter(language!!, this)
            recyclerView?.layoutManager = LinearLayoutManager(activity, LinearLayout.VERTICAL, false)
            recyclerView?.setAdapter(adapter)

        }
    }

    fun handleLanguageError(error: Throwable) {
        println("Error ${error.localizedMessage}")
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
    }

    fun translationApiCall(code: String) {
        progress = ProgressDialog.show(
                activity!!, null,
                "Preparing... ", true
        )
        compositeDisposable?.add(apiService.getTranslations(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTranslationResponse, this::handleTranslationError))
    }

    fun handleTranslationResponse(translationList: List<TranslationsModel>) {
        if (translationList.size > 0 && translationList != null) {
            navigateToLogin(gson?.toJson(translationList)!!)
        }
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
    }

    fun handleTranslationError(error: Throwable) {
        println("Error ${error.localizedMessage}")
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }

    }

    fun navigateToLogin(json: String) {
        val intent = Intent(activity, LoginActivity::class.java)
        intent.putExtra(Constants.KEY_TRANSLATION_RESPONSE, json)
        startActivity(intent)
    }
}