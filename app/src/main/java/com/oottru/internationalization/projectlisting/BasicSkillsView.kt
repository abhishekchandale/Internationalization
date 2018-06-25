package com.oottru.internationalization.projectlisting

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.oottru.internationalization.R

class BasicSkillsView : LinearLayout {

    private var recyclerView: RecyclerView? = null
    private var title: TextView? = null
    private var layoutManager: LinearLayoutManager? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        title = findViewById(R.id.detail_backskills_title)
        title!!.text = context.getString(R.string.skill_projectdetail_basicskills_text)
        recyclerView = findViewById(R.id.detail_basicskills_list)
        recyclerView!!.setHasFixedSize(true)
        layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.isFocusable = false
    }
/*
    fun bindSkillsList(detail: PBProjectDetail) {
        val adapter = DetailBaseSkillsAdapter(context, detail.getComplexity().getDifficulty())
        recyclerView!!.adapter = adapter
    }*/
}
