package com.oottru.internationalization.projectlisting

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.TypedValue
import android.widget.LinearLayout
import android.widget.TextView

import com.oottru.internationalization.R

class DescriptionView : LinearLayout {

    private var mTitle: TextView? = null
    private var mDescription: TextView? = null
    private var cxt: Context? = null

    constructor(context: Context) : super(context) {
        this.cxt = context
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        this.cxt = context
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        this.cxt = context
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        this.cxt = context
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        mTitle = findViewById(R.id.detail_description_title)
        mDescription = findViewById(R.id.detail_description)

    }

    /*public void bindDescription(PBProjectDetail description) {
        this.mDescription.setText(description.getDescription());
    }*/


    fun setTextSize(size: Float) {
        mDescription!!.setTextSize(TypedValue.COMPLEX_UNIT_PX, size)
    }
}

