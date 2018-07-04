package com.oottru.internationalization.fragment

import com.oottru.internationalization.BaseView


interface ProjectListContract {
    interface View : BaseView<Presenter> {
        fun sayHello()
    }

    interface Presenter {
        fun start()
        fun displayHello()
    }
}