package com.oottru.internationalization.Util

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.support.annotation.RequiresApi
import android.text.InputFilter
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.oottru.internationalization.R
import java.util.*


class CreateViewElement constructor(val context: Context) {


    companion object {
        const val KEY_EDIT_TEXT: String = "inputText"
        const val KEY_TEXT_VIEW: String = "tx"
        const val KEY_BUTTON: String = "btn"
        const val KEY_DROP_DOWN: String = "dropdown"
        const val KEY_DATE: String = "date"
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun getItemViewType(type: String): View {

        val layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(dpToPx(30), 20, dpToPx(30), 0)
        var v: View? = null
        if (KEY_EDIT_TEXT == type) {
            val editText: EditText = EditText(context)
            editText.layoutParams = layoutParams
            editText.id = Random().nextInt()
            editText.background = (context.resources.getDrawable(R.drawable.sign_in_ed_bg))
            editText.inputType = InputType.TYPE_CLASS_TEXT
            v = editText
        }
        if (KEY_TEXT_VIEW == type) {
            val textView = TextView(context)
            textView.layoutParams = layoutParams
            textView.id = Random().nextInt()
            v = textView
        }
        if (KEY_BUTTON == type) {
            val button = Button(context)
            button.layoutParams = layoutParams
            button.id = Random().nextInt()
            v = button
        }
        if (KEY_DROP_DOWN == type) {
            val spinner = Spinner(context)
            spinner.layoutParams = layoutParams
            spinner.id = Random().nextInt()
            v = spinner
        }

        return v!!
    }

    private fun getInputType(text: String): Int {
        when (text) {
            "number" -> return InputType.TYPE_CLASS_NUMBER
            "phone" -> return InputType.TYPE_CLASS_PHONE
            "email" -> return InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            "password" -> return InputType.TYPE_TEXT_VARIATION_PASSWORD
            else -> return InputType.TYPE_CLASS_TEXT
        }
    }

    private fun setMaxLength(editText: EditText, maxLength: Int) {
        val fArray = arrayOfNulls<InputFilter>(1)
        fArray[0] = InputFilter.LengthFilter(maxLength)
        editText.filters = fArray
    }

    fun dpToPx(dp: Int): Int {
        return (dp * Resources.getSystem().getDisplayMetrics().density).toInt();
    }

    fun pxToDp(px: Int): Int {
        return (px / Resources.getSystem().getDisplayMetrics().density).toInt()
    }
}