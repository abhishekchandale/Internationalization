package com.oottru.internationalization.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oottru.internationalization.R


class ProjectDetailFragment : Fragment(), ProjectDetailContract.View {
    override lateinit var presenter: ProjectDetailContract.Presenter

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Create the presenter
        presenter = ProjectDetailPresenter()
        return inflater.inflate(R.layout.fragment_two, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // textView1.text = "Fragment 2 Loaded"
    }


    companion object {

        fun newInstance() = ProjectDetailFragment()
    }

}