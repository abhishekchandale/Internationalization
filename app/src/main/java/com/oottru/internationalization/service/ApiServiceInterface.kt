package com.oottru.internationalization.service

import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.model.DummyDataModel
import com.oottru.internationalization.model.DummyModel
import com.oottru.internationalization.model.LanguageModel
import com.oottru.internationalization.model.TranslationsModel
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String): Observable<DummyModel.Result>


    @GET("movies_2017.json")
    fun getImages(): Observable<List<DummyDataModel>>


    @GET("language")
    fun getLanguagePreferences(): Observable<List<LanguageModel>>

    @GET("translation")
    fun getTranslations(@Query("code") code: String): Observable<List<TranslationsModel>>

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