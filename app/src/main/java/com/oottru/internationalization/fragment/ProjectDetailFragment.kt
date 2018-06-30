package com.oottru.internationalization.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.model.ProjectModel
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.fragment_project_list_detail.*
import kotlinx.android.synthetic.main.widget_detail_basicskills_view.*
import kotlinx.android.synthetic.main.widget_detail_description_view.*
import kotlinx.android.synthetic.main.widget_detail_finished_size_view.*
import kotlinx.android.synthetic.main.widget_detail_header_view.*
import kotlinx.android.synthetic.main.widget_detail_material_view.*


class ProjectDetailFragment : Fragment(), ProjectDetailContract.View {

    override lateinit var presenter: ProjectDetailContract.Presenter
    var tempIntent: String? = null
    var model: ProjectModel? = null
    var gson: Gson? = null

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
        model = gson?.fromJson(tempIntent, ProjectModel::class.java)

        Glide.with(this).load(model?.picture)
                .into(expandedImage)


        project_item_txt.setText(model?.name)
        detail_difficulty.setText(model?.complexity)
        detail_duration.setText(model?.duration)
        //detail_project_quote.setText(model?.)
        detail_description_title.setText(model?.desc_title)
        detail_description.setText(model?.desc_value)
        detail_finishedsize_title.setText(model?.finished_size_title)
        detail_finishedsize.setText(model?.finished_size_value)
        detail_material_title.setText(model?.materials_cut_title)
        detail_materials.setText(model?.materials_cut_value)
        detail_other_material_title.setText(model?.everything_else_title)
        detail_other_materials.setText(model?.everything_else_value)
        detail_backskills_title.setText(model?.prepariton_title)
        item_price.text= model?.price.toString()



        // textView1.text = "Fragment 2 Loaded"
    }


    companion object {

        fun newInstance() = ProjectDetailFragment()
    }

}


