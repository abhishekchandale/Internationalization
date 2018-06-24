package com.oottru.internationalization.fragment

import com.oottru.internationalization.BaseView


/**
 * Created by marco on 08/11/17.
 */
interface ProjectListContract {
    interface View: BaseView<Presenter> {

    }
    interface Presenter{
        fun start()
    }
}