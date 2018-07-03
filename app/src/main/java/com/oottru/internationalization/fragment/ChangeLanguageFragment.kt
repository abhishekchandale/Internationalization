package com.oottru.internationalization.fragment

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.google.gson.Gson
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Common
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.Util.Prefs
import com.oottru.internationalization.fragment.adapter.ChangeLanguageAdapter
import com.oottru.internationalization.model.LanguageModel
import com.oottru.internationalization.model.TranslationApiResponse
import com.oottru.internationalization.service.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ChangeLanguageFragment : Fragment() {
    private var mView: View? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var progress: ProgressDialog? = null
    private var gson: Gson? = null
    private var language: ArrayList<LanguageModel>? = null
    private var recyclerView: RecyclerView? = null
    private var prefs: Prefs? = null
    private var txSelectedLang: TextView? = null
    lateinit var listener: LanguageChangeListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_language, container, false)
        return mView
    }

    private val apiService by lazy {
        ApiServiceInterface.create()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is LanguageChangeListener) {
            listener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement LanguageChangeListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = mView?.findViewById(R.id.language_recycler) as RecyclerView
        txSelectedLang = mView?.findViewById(R.id.tx_selected_lang) as TextView
        gson = Gson()
        prefs = Prefs(this.activity!!)
        if (prefs?.language != null) {
            txSelectedLang!!.text = prefs?.language
        }
        compositeDisposable = CompositeDisposable()
        if (Common.isNetworkAvailable(this.activity!!)) {
            languageApiCall()
        } else {

        }

    }


    companion object {
        fun newInstance() = ChangeLanguageFragment()
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
            val adapter = ChangeLanguageAdapter(language!!, this)
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
        compositeDisposable?.add(apiService.getTranslationsChange(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTranslationResponse, this::handleTranslationError))
    }

    fun handleTranslationResponse(translationList: TranslationApiResponse) {
        if (translationList.Translation_Masters.size > 0 && translationList != null) {
            prefs?.transaltion = gson?.toJson(translationList)!!
            if (prefs?.isLogin == false) {
                navigateTo(SignInFragment.newInstance(), gson?.toJson(translationList)!!)
            } else {
                navigateTo(ProjectListFragment.newInstance(), "")
                listener.updateLanguage(gson?.toJson(translationList)!!)
            }
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

    private fun navigateTo(fragment: Fragment, response: String) {
        var bundle = Bundle()
        if (response != "") {
            bundle.putString(Constants.KEY_TRANSLATION_RESPONSE, response)
            fragment.arguments = bundle
        } else {
            fragmentManager?.beginTransaction()
                    ?.replace(R.id.contentFrame, fragment)?.commit()
        }

    }

    interface LanguageChangeListener {
        fun updateLanguage(l: String)
    }

}