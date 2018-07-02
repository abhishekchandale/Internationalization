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
import com.oottru.internationalization.R.id.*
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
                // project_item_txt.setText(index.value)
                project_item_txt.setText(model?.name)
            }
            if (Constants.PROJECT_DETAIL_DESCRIPTION.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_description_title.setText(index.value)
                detail_description.setText(model?.desc_value)
            }
            if (Constants.PROJECT_DETAIL_FINISHED_SIZE.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_finishedsize_title.setText(index.value)
                detail_finishedsize.setText(model?.finished_size_value)
            }
            if (Constants.PROJECT_DETAIL_MATERIALS_TO_CUT.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_material_title.setText(index.value)
                detail_materials.setText(model?.materials_cut_value)
            }
            if (Constants.PROJECT_DETAIL_EVERYTHING_ELSE.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_everythingl_title.setText(index.value)
                detail_everythingelse.setText(model?.everything_else_value)
            }
            if (Constants.PROJECT_DETAIL_CUT.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_cut_tittle.setText(index.value)
                detail_cut_value.setText(model?.cut_value)
            }
            if (Constants.PROJECT_DETAIL_PREPARATION.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_preperation_title.setText(index.value)
                detail_preperation_value.setText(model?.prepariton_value)
            }

            if (Constants.PROJECT_DETAIL_PREPARATION.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_preperation_title.setText(index.value)
                detail_preperation_value.setText(model?.prepariton_value)
            }
            if (Constants.PROJECT_DETAIL_CUSTOMIZE_BTN.toLowerCase() == index.resource_key.toLowerCase()) {
                btn_custom.setText(index.value)

            }
            if (Constants.PROJECT_DETAIL_MAKE_IT_BTN.toLowerCase() == index.resource_key.toLowerCase()) {
                btn_makeit.setText(index.value)

            }
            if (Constants.PROJECT_DETAILS_DIFFICULTY_LEVEL.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_difficulty.setText(model?.complexity)

            }
            if (Constants.PROJECT_DETAILS_COMPLETION_TIME.toLowerCase() == index.resource_key.toLowerCase()) {
                detail_duration.setText(model?.duration)
            }
            if (Constants.PROJECT_DETAILS_PRICE.toLowerCase() == index.resource_key.toLowerCase()) {
                item_price.text = model?.price.toString()
            }
        }


    }

    companion object {

        fun newInstance() = ProjectDetailFragment()
    }

}


