package com.oottru.internationalization.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.Prefs
import com.oottru.internationalization.fragment.LanguagePrefFragment
import com.oottru.internationalization.model.LanguageModel


class LanguagePrefAdapter(val languageList: ArrayList<LanguageModel>, val context: LanguagePrefFragment) : RecyclerView.Adapter<LanguagePrefAdapter.ViewHolder>() {
    private var selectedPosition = -1
    private var prefs: Prefs? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagePrefAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_language_item, parent, false)
        prefs = Prefs(parent.context)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: LanguagePrefAdapter.ViewHolder, position: Int) {
        holder.textViewName.text = languageList.get(position).language
        if (position == selectedPosition) {
            holder.checkboxLanguage.setChecked(true);
        } else {
            holder.checkboxLanguage.setChecked(false)
        }
        holder.checkboxLanguage.setOnClickListener {
            if (holder.checkboxLanguage.isChecked) {
                selectedPosition = position
                prefs?.language=languageList.get(position).locale_code
                context.translationApiCall(languageList.get(position).locale_code)
            } else {
                selectedPosition = -1
            }
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName = itemView.findViewById(R.id.tx_language) as TextView
        //val switchLanguage = itemView.findViewById(R.id.switch_language) as Switch
        val checkboxLanguage = itemView.findViewById(R.id.checkbox_language) as CheckBox
    }

}