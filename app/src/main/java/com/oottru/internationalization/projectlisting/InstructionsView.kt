package com.oottru.internationalization.projectlisting

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.widget.LinearLayout
import com.oottru.internationalization.R

class InstructionsView : LinearLayout {

    private var recyclerView: RecyclerView? = null
    private var layoutManager: LinearLayoutManager? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        recyclerView = findViewById(R.id.detail_instructions_list)
        recyclerView!!.setHasFixedSize(true)
        layoutManager = object : LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) {
            override fun canScrollVertically(): Boolean {
                return false
            }
        }
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.isFocusable = false
    }

    /*fun bindInstructions(detail: PBProjectDetail) {
        val adapter = DetailInstructionsAdapter(context, detail.getInstructions().getSectionsList(),
                detail.getPrintInstructionsList())
        recyclerView!!.adapter = adapter
    }*/
}
