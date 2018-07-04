package com.oottru.internationalization.fragment

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Prefs
import com.oottru.internationalization.fragment.adapter.ProjectListAdapter
import com.oottru.internationalization.model.ProjectModel
import com.oottru.internationalization.model.TranslationApiResponse
import io.reactivex.disposables.CompositeDisposable


class ProjectListFragment : Fragment(), ProjectListContract.View {

    override fun sayHello() {

    }

    private var mView: View? = null
    private var recycler: RecyclerView? = null
    private var layoutManager: GridLayoutManager? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var progress: ProgressDialog? = null
    private var prefs: Prefs? = null
    private var translationApiResponse: TranslationApiResponse? = null
    private var gson: Gson? = null

    override lateinit var presenter: ProjectListContract.Presenter
    companion object {
        fun newInstance() = ProjectListFragment()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        presenter = ProjectListPresenter()
        mView = inflater.inflate(R.layout.fragment_one, container, false)
        return mView

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = mView?.findViewById(R.id.recyclerView) as RecyclerView
        compositeDisposable = CompositeDisposable()
        prefs = Prefs(this.activity!!)
        loadJSON(prefs?.transaltion!!)
    }


    private fun loadJSON(translation: String) {
        progress = ProgressDialog.show(
                activity!!, null,
                "Loading... ", true
        )
        gson = Gson()
        translationApiResponse = gson?.fromJson(translation, TranslationApiResponse::class.java)
        handleResponse(translationApiResponse?.Values!!)
    }

    private fun handleResponse(projectModel: List<ProjectModel>) {
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
        if (projectModel != null && projectModel.size > 0) {
            val adapter = ProjectListAdapter(projectModel, this)
            layoutManager = GridLayoutManager(this.activity!!, 2, GridLayoutManager.VERTICAL, false)
            recycler?.layoutManager = layoutManager
            recycler?.setAdapter(adapter)
        }

    }
}
