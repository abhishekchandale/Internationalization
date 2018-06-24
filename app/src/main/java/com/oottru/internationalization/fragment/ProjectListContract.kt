package com.oottru.internationalization.fragment

import com.oottru.internationalization.BaseView


interface ProjectListContract {
    interface View : BaseView<Presenter> {

    }

    interface Presenter {
        fun start()
    }
}