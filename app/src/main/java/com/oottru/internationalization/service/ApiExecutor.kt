package com.oottru.internationalization.service

import com.google.gson.Gson
import com.oottru.internationalization.model.TranslationsModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ApiExecutor() {
    companion object {
        private val mInstance: ApiExecutor = ApiExecutor()

        @Synchronized
        fun getInstance(): ApiExecutor {
            return mInstance
        }
    }

    private val apiService by lazy {
        ApiServiceInterface.create()
    }
    var compositeDisposable: CompositeDisposable? = null
    var gson: Gson? = null

    init {
        compositeDisposable = CompositeDisposable()
    }

    fun translationApiCall(code: String) {

        compositeDisposable?.add(apiService.getTranslations(code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleTranslationResponse, this::handleTranslationError))
    }

    fun handleTranslationResponse(translationList: List<TranslationsModel>) {
        if (translationList.size > 0 && translationList != null) {
        }

    }

    fun handleTranslationError(error: Throwable) {
        println("Error ${error.localizedMessage}")

    }

    fun clearComposite() {
        compositeDisposable?.clear()
    }

    interface ApiCallback {

    }

}