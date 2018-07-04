package com.oottru.internationalization.fragment

import android.util.Log

class ProjectListPresenter : ProjectListContract.Presenter {
    override fun displayHello() {

    }

    override fun start() {
        Log.d("ProjectListPresenter", "start")
    }
}