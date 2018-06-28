package com.oottru.internationalization.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Switch
import android.widget.TextView
import com.oottru.internationalization.R
import com.oottru.internationalization.fragment.LanguagePrefFragment
import com.oottru.internationalization.model.LanguageModel
import com.oottru.internationalization.service.ApiExecutor


class LanguagePrefAdapter(val languageList: ArrayList<LanguageModel>, val context: LanguagePrefFragment) : RecyclerView.Adapter<LanguagePrefAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguagePrefAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_language_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: LanguagePrefAdapter.ViewHolder, position: Int) {
        holder.bindItems(languageList[position], context)

    }

    override fun getItemCount(): Int {
        return languageList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(language: LanguageModel, context: LanguagePrefFragment) {
            val textViewName = itemView.findViewById(R.id.tx_language) as TextView
            val switchLanguage = itemView.findViewById(R.id.switch_language) as Switch
            textViewName.text = language.language
            switchLanguage.setOnCheckedChangeListener(
                    CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                        if (isChecked) {
                            var api: ApiExecutor = ApiExecutor()
                            //  api.translationApiCall(language.locale_code)
                            context.translationApiCall(language.locale_code)
                        } else {

                        }
                    })
        }
    }


}