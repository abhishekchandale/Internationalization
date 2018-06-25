package com.oottru.internationalization.projectlisting

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.text.Html
import android.text.TextUtils
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.oottru.internationalization.R

class DetailHeaderView : LinearLayout {

    private var itemTitle: TextView? = null
    private var difficulty: TextView? = null
    private var duration: TextView? = null
    private var quote: TextView? = null
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

        itemTitle = findViewById(R.id.project_item_txt)
        difficulty = findViewById(R.id.detail_difficulty)
        duration = findViewById(R.id.detail_duration)
        quote = findViewById(R.id.detail_project_quote)


    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
    }


}
