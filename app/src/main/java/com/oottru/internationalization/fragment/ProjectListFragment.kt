package com.oottru.internationalization.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oottru.internationalization.R
import com.oottru.internationalization.fragment.adapter.ProjectListAdapter


class ProjectListFragment : Fragment(), ProjectListContract.View {
    var items: ArrayList<String>? = null
    var mView: View? = null
    var recycler: RecyclerView? = null
    var layoutManager: GridLayoutManager? = null
    override lateinit var presenter: ProjectListContract.Presenter


    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Create the presenter
        presenter = ProjectListPresenter()
        mView = inflater.inflate(R.layout.fragment_one, container, false)
        return mView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //textView1.text = "Fragment 1 Loaded"
        recycler = mView?.findViewById(R.id.recyclerView) as RecyclerView
        callAdapter()
    }

    companion object {

        fun newInstance() = ProjectListFragment()
    }

    fun callAdapter() {
        items = ArrayList()
        items?.add("Mothers Day")
        items?.add("Mothers Day")
        items?.add("Mothers Day")
        items?.add("Mothers Day")
        items?.add("Mothers Day")
        items?.add("Mothers Day")
        items?.add("Mothers Day")
        items?.add("Mothers Day")
        items?.add("Mothers Day")


        val adapter = ProjectListAdapter(items!!, this.activity!!)
        layoutManager = GridLayoutManager(this.activity!!, 2, GridLayoutManager.VERTICAL, false)
        recycler?.layoutManager = layoutManager
        recycler?.setAdapter(adapter)


    }
}