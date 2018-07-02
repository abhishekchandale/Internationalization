package com.oottru.internationalization.projectlisting

import android.content.Context
import android.support.percent.PercentRelativeLayout
import android.text.Html
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.oottru.internationalization.R

class MaterialView : PercentRelativeLayout {

    private var materialTitle: TextView? = null
    private var otherMaterialTitle: TextView? = null
    private var materials: TextView? = null
    private var otherMaterials: TextView? = null
    private var materialSegment: LinearLayout? = null
    private var otherMaterialSegment: LinearLayout? = null
    private var materialTipLayout: LinearLayout? = null
    private var materialTipText: TextView? = null
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

    override fun onFinishInflate() {
        super.onFinishInflate()

        materialTitle = findViewById(R.id.detail_material_title)
        materials = findViewById(R.id.detail_materials)
        materialSegment = findViewById(R.id.detail_material_segment)


    }

}
