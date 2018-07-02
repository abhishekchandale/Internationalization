package com.oottru.internationalization.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.Util.Prefs
import com.oottru.internationalization.model.ProjectModel
import com.oottru.internationalization.model.TranslationsModel
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.everythingelse_view.*
import kotlinx.android.synthetic.main.fragment_project_list_detail.*
import kotlinx.android.synthetic.main.widget_detail_description_view.*
import kotlinx.android.synthetic.main.widget_detail_finished_size_view.*
import kotlinx.android.synthetic.main.widget_detail_header_view.*
import kotlinx.android.synthetic.main.widget_detail_instructions_view.*
import kotlinx.android.synthetic.main.widget_detail_material_view.*
import kotlinx.android.synthetic.main.widget_preperation_view.*


class ProjectDetailFragment : Fragment(), ProjectDetailContract.View {

    override lateinit var presenter: ProjectDetailContract.Presenter
    var tempIntent: String? = null
    var model: ProjectModel? = null
    var gson: Gson? = null
    private var transaltionObj: ArrayList<TranslationsModel>? = null
    private var prefs: Prefs? = null

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Create the presenter
        presenter = ProjectDetailPresenter()
        return inflater.inflate(R.layout.fragment_project_list_detail, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tempIntent = arguments?.getString(Constants.KEY_DETAIL_RESPONSE)
        gson = Gson()
        prefs = Prefs(this.activity!!)
        model = gson?.fromJson(tempIntent, ProjectModel::class.java)
        val listType = object : TypeToken<List<TranslationsModel>>() {}.type
        transaltionObj = gson?.fromJson(prefs?.transaltion, listType)
        Glide.with(this).load(model?.picture)
                .into(expandedImage)

        for (index in transaltionObj!!) {
            if (Constants.PROJECT_DETAIL_PROJECT_NAME.toLowerCase() == index.resource_key.toLowerCase()) {
                //   project_item_txt.setText(index.value)
            }
            if (Constants.PROJECT_DETAIL_PROJECT_NAME.toLowerCase() == index.resource_key.toLowerCase()) {

            }

            //TODO
        }

        project_item_txt.setText(model?.name)
        detail_difficulty.setText(model?.complexity)
        detail_duration.setText(model?.duration)
        detail_description_title.setText(model?.desc_title)
        detail_description.setText(model?.desc_value)
        detail_finishedsize_title.setText(model?.finished_size_title)
        detail_finishedsize.setText(model?.finished_size_value)
        detail_material_title.setText(model?.materials_cut_title)
        detail_materials.setText(model?.materials_cut_value)
        detail_everythingl_title.setText(model?.everything_else_title)
        detail_everythingelse.setText(model?.everything_else_value)
        detail_cut_tittle.setText(model?.cut_title)
        detail_cut_value.setText(model?.cut_value)
        detail_preperation_title.setText(model?.prepariton_title)
        detail_preperation_value.setText(model?.prepariton_value)
        item_price.text = model?.price.toString()


        // textView1.text = "Fragment 2 Loaded"
    }


    companion object {

        fun newInstance() = ProjectDetailFragment()
    }

}


