package com.oottru.internationalization

interface MainContract {
    interface View {
        fun showLoading()
        fun showFragment_One()
        fun showFragment_Two()
    }

    interface Presenter {
        fun start()
        //fun loadMain()
    }
}