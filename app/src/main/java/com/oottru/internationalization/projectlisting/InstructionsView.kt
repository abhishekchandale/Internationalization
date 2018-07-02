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

class InstructionsView : LinearLayout {

    private var cutTitle: TextView? = null
    private var cutValue: TextView? = null


    private var layoutManager: LinearLayoutManager? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()


        cutTitle = findViewById(R.id.detail_cut_tittle)
        cutValue = findViewById(R.id.detail_cut_value)
        }
    }


