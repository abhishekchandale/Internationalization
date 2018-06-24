package com.oottru.internationalization.fragment

import com.oottru.internationalization.BaseView

/**
 * Created by marco on 09/11/17.
 */
interface ProjectDetailContract {
    interface View: BaseView<Presenter> {

    }
    interface Presenter{
        fun start()
    }
}