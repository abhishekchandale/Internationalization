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
        otherMaterialTitle = findViewById(R.id.detail_other_material_title)
        materials = findViewById(R.id.detail_materials)
        otherMaterials = findViewById(R.id.detail_other_materials)
        materialSegment = findViewById(R.id.detail_material_segment)
        otherMaterialSegment = findViewById(R.id.detail_other_material_segment)
        materialTipLayout = findViewById(R.id.material_tip_layout)
        materialTipText = findViewById(R.id.material_tips)

    }

/*
    fun bindMaterial(detail: PBProjectDetail) {

        var sb = StringBuilder()
        val cricutCuts = detail.getMaterialsUsed().getCricutCutList()
        for (cut in cricutCuts) {
            sb.append("\u2022 ").append(Html.fromHtml(cut.getName())).append("\n")
        }
        val otherCut = detail.getMaterialsUsed().getOtherCutList()
        for (cut in otherCut) {
            sb.append("\u2022 ").append(Html.fromHtml(cut)).append("\n")
        }
        if (sb.toString().isEmpty()) {
            this.materialSegment!!.visibility = View.GONE
        } else {
            this.materials!!.text = sb.toString()
        }

        sb = StringBuilder()
        val cricutOthers = detail.getMaterialsUsed().getCricutOtherList()
        for (material in cricutOthers) {
            sb.append("\u2022 ").append(Html.fromHtml(material.getName())).append("\n")
        }
        val materials = detail.getMaterialsUsed().getOtherOtherList()
        for (material in materials) {
            sb.append("\u2022 ").append(Html.fromHtml(material)).append("\n")
        }
        if (sb.toString().isEmpty()) {
            this.otherMaterialSegment!!.visibility = View.GONE
        } else {
            this.otherMaterials!!.text = sb.toString()
        }

        val materialTip = detail.getMaterialsUsed().getMaterialTip().getName()
        if (!materialTip.isEmpty()) {
            materialTipLayout!!.visibility = View.VISIBLE
            materialTipText!!.setText(materialTip)
        }
    }*/
}
