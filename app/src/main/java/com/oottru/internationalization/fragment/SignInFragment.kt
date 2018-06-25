package com.oottru.internationalization.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oottru.internationalization.R
import com.oottru.internationalization.model.ResourceJSON
import java.io.IOException


class SignInFragment : Fragment() {

    var gson: Gson? = null
    var mView: View? = null
    var lblEmail: TextView? = null
    var lblPassword: TextView? = null
    var edEmail: EditText? = null
    var edPassword: EditText? = null
    var resourceJSon: List<ResourceJSON>? = null

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        gson = Gson()
        val listType = object : TypeToken<List<ResourceJSON>>() {
        }.type

        val json = readResourceJson()
        if (json == null) return else resourceJSon = gson?.fromJson(json, listType);

        if (resourceJSon != null)
            println("Resources are present ${resourceJSon?.size}")

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        mView = inflater.inflate(R.layout.fragment_sign_in, container, false)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lblEmail = mView?.findViewById(R.id.lbl_email) as TextView
        lblPassword = mView?.findViewById(R.id.lbl_password) as TextView
        edEmail = mView?.findViewById(R.id.etEmail) as EditText
        edPassword = mView?.findViewById(R.id.etPassword) as EditText
        if (resourceJSon != null) {
            lblEmail?.text = resourceJSon?.get(0)?.value
            lblPassword?.text = resourceJSon?.get(1)?.value
            edEmail?.hint = resourceJSon?.get(0)?.value
            edPassword?.hint = resourceJSon?.get(1)?.value

        }
    }


    companion object {
        fun newInstance() = SignInFragment()
    }

    fun readResourceJson(): String {

        var json: String? = null
        try {
            val jsonFile = activity!!.assets.open("resource.json")
            val size = jsonFile.available()
            val buffer = ByteArray(size)
            jsonFile.read(buffer)
            jsonFile.close()
            json = String(buffer)
            println("JSON READ ${json}")
        } catch (ex: IOException) {
            ex.printStackTrace()
            println("JSON FAIL  READ ${ex.printStackTrace()}")

        }
        return json.toString()
    }


}