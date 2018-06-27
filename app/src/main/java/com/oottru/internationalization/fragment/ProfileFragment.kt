package com.oottru.internationalization.fragment

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.CreateViewElement


class ProfileFragment : Fragment() {

    var mView: View? = null
    var mChildLayout: LinearLayout? = null
    var mCreatView: CreateViewElement? = null

    companion object {
        fun newInstance() = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_profile, container, false)
        return mView
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mChildLayout = mView?.findViewById(R.id.layout_child) as LinearLayout
        mCreatView = CreateViewElement(this.activity!!)
        inflateView()
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun inflateView() {
        mChildLayout?.addView(mCreatView?.getItemViewType(CreateViewElement.KEY_EDIT_TEXT))
        mChildLayout?.addView(mCreatView?.getItemViewType(CreateViewElement.KEY_EDIT_TEXT))
        mChildLayout?.addView(mCreatView?.getItemViewType(CreateViewElement.KEY_EDIT_TEXT))
        mChildLayout?.addView(mCreatView?.getItemViewType(CreateViewElement.KEY_EDIT_TEXT))
    }
}