package com.oottru.internationalization.fragment.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.oottru.internationalization.R
import com.oottru.internationalization.model.ProjectModel
import kotlinx.android.synthetic.main.fragment_project_list_item.view.*

class ProjectListAdapter(val items: ArrayList<ProjectModel>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_project_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.txtTitle.text = items.get(position).name
        Glide.with(context).load(items.get(position).picture)
                .into(holder?.imgProject);
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val txtTitle = view.txt_title
    val imgProject = view.img_project
}
