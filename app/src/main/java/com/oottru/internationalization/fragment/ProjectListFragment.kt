package com.oottru.internationalization.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oottru.internationalization.R
import com.oottru.internationalization.fragment.adapter.ProjectListAdapter
import com.oottru.internationalization.model.ProjectModel
import com.oottru.internationalization.service.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class ProjectListFragment : Fragment(), ProjectListContract.View {
    private var mView: View? = null
    private var recycler: RecyclerView? = null
    private var layoutManager: GridLayoutManager? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var mArrayList: ArrayList<ProjectModel>? = null
    private var progress:ProgressDialog?=null

    override lateinit var presenter: ProjectListContract.Presenter

    private val apiService by lazy {
        ApiServiceInterface.create()
    }

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
        recycler = mView?.findViewById(R.id.recyclerView) as RecyclerView
        compositeDisposable = CompositeDisposable()
        loadJSON()
    }

    companion object {
        fun newInstance() = ProjectListFragment()
    }

    private fun loadJSON() {
        progress = ProgressDialog.show(
                activity!!, null,
                "Loading... ", true
        )
        compositeDisposable?.add(apiService.getProjectList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError))

    }

    private fun handleResponse(androidList: List<ProjectModel>) {
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
        mArrayList = ArrayList(androidList)
        val adapter = ProjectListAdapter(mArrayList!!, this.activity!!)
        layoutManager = GridLayoutManager(this.activity!!, 2, GridLayoutManager.VERTICAL, false)
        recycler?.layoutManager = layoutManager
        recycler?.setAdapter(adapter)
    }

    private fun handleError(error: Throwable) {
        println("Error ${error.localizedMessage}")
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable?.clear()
    }
}