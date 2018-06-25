package com.oottru.internationalization.Util

import android.text.InputFilter
import android.text.InputType
import android.widget.EditText

class CreateViewElement() {


    companion object {
        val EDIT_TEXT: String = "edittext"
        val TEXT_VIEW_TEXT: String = "textview"
        val BUTTON: String = "button"
    }

    fun createViewByType(type: String) {
        when (type) {

        }
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
        editText.setFilters(fArray)
    }
}