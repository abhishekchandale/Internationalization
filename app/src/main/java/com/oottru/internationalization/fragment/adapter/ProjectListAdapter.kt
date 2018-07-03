package com.oottru.internationalization.fragment.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Constants
import com.oottru.internationalization.fragment.ProjectDetailFragment
import com.oottru.internationalization.fragment.ProjectListFragment
import com.oottru.internationalization.model.ProjectModel
import kotlinx.android.synthetic.main.fragment_project_list_item.view.*

class ProjectListAdapter(val items: List<ProjectModel>, val context: ProjectListFragment) : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {

    lateinit var ctx: ProjectListFragment
    var gson: Gson? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        gson = Gson()
        ctx = context
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_project_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.txtTitle.text = items.get(position).name
        Glide.with(context).load(items.get(position).picture)
                .into(holder?.imgProject)

        holder.cardView.setOnClickListener {

            navigateTo(ProjectDetailFragment.newInstance(), gson?.toJson(items.get(position))!!)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun navigateTo(fragment: Fragment, response: String) {
        var bundle = Bundle()
        if (response != "") {
            bundle.putString(Constants.KEY_DETAIL_RESPONSE, response)
            fragment.arguments = bundle

            ctx.fragmentManager?.beginTransaction()?.replace(R.id.contentFrame, fragment)?.commit()
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle = view.txt_title
        val imgProject = view.img_project
        val cardView = view.cardview_list
    }


}