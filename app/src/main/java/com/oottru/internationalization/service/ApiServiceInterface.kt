package com.oottru.internationalization.service

import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.model.*
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {


    @GET("projects")
    fun getProjectList(@Query("code") code: String): Observable<List<ProjectModel>>

    @GET("language")
    fun getLanguagePreferences(): Observable<List<LanguageModel>>

    @GET("translation")
    fun getTranslations(@Query("code") code: String): Observable<List<TranslationsModel>>

    @GET("profiles")
    fun getProfile(): Observable<List<ProfileModel>>

    companion object {
        fun create(): ApiServiceInterface {

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.baseUrl)
                    .build()
            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}