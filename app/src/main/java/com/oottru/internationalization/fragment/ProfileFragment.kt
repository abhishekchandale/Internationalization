package com.oottru.internationalization.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import com.oottru.internationalization.R
import com.oottru.internationalization.Util.CreateViewElement
import com.oottru.internationalization.model.ProfileModel
import com.oottru.internationalization.service.ApiServiceInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*


class ProfileFragment : Fragment() {

    private var mView: View? = null
    private var mChildLayout: LinearLayout? = null
    private var mCreatView: CreateViewElement? = null
    private var compositeDisposable: CompositeDisposable? = null
    private var progress: ProgressDialog? = null
    private var gson: Gson? = null
    private var dropDownList: ArrayList<String>? = null
    private var editable: Editable? = null
    private var myCalendar: Calendar? = null

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private val apiService by lazy {
        ApiServiceInterface.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_profile, container, false)
        return mView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mChildLayout = mView?.findViewById(R.id.layout_child) as LinearLayout
        mCreatView = CreateViewElement(this.activity!!)
        compositeDisposable = CompositeDisposable()
        gson = Gson()
        profileApiCall()
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun inflateView(profile: List<ProfileModel>) {
        var localView: View? = null
        for (index in profile) {
            try {
                if (CreateViewElement.KEY_EDIT_TEXT == index.ctl_type) {
                    localView = mCreatView?.getItemViewType(CreateViewElement.KEY_TEXT_VIEW)
                    localView as TextView
                    localView.setText(index.ctl_lable)
                    mChildLayout?.addView(localView)
                    localView = mCreatView?.getItemViewType(CreateViewElement.KEY_EDIT_TEXT)
                    localView as EditText
                    localView.hint = index.ctl_lable!!
                    editable = SpannableStringBuilder(index.ctl_value!!)
                    localView.text = editable
                    mChildLayout?.addView(localView)
                }
                if (CreateViewElement.KEY_DROP_DOWN == index.ctl_type) {
                    localView = mCreatView?.getItemViewType(CreateViewElement.KEY_TEXT_VIEW)
                    localView as TextView
                    localView.setText(index.ctl_lable)
                    mChildLayout?.addView(localView)
                    localView = mCreatView?.getItemViewType(CreateViewElement.KEY_DROP_DOWN)
                    localView as Spinner
                    localView.setBackgroundResource(R.drawable.sppinner_bg)
                    var temp = (Arrays.asList((index.ctl_value).split(","))).get(0)
                    dropDownList = ArrayList()
                    dropDownList?.addAll(temp)
                    val adapter = ArrayAdapter<String>(this.activity!!, android.R.layout.simple_spinner_dropdown_item, dropDownList)
                    localView.adapter = adapter
                    mChildLayout?.addView(localView)
                }
                if (CreateViewElement.KEY_DATE == index.ctl_type) {
                    myCalendar = Calendar.getInstance()
                    val myFormat = "MM/dd/yy" //In which you need put here
                    val sdf = SimpleDateFormat(myFormat, Locale.US)
                    localView = mCreatView?.getItemViewType(CreateViewElement.KEY_TEXT_VIEW)
                    localView as TextView
                    localView.setText(index.ctl_lable)
                    mChildLayout?.addView(localView)
                    localView = mCreatView?.getItemViewType(CreateViewElement.KEY_EDIT_TEXT)
                    localView as EditText
                    localView.hint = index.ctl_lable
                    localView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_date_range, 0);
                    editable = SpannableStringBuilder(sdf.format(myCalendar?.time))
                    localView.isFocusable = false
                    val datePicker = DatePickerFragment()
                    localView.setOnClickListener {
                        datePicker.show(fragmentManager, "DatePickerFragment")
                    }
                    mChildLayout?.addView(localView)
                }
                if (CreateViewElement.KEY_BUTTON == index.ctl_type) {
                    localView = mCreatView?.getItemViewType(CreateViewElement.KEY_BUTTON)
                    localView as Button
                    localView.setText(index.ctl_value)
                    mChildLayout?.addView(localView)
                }
            } catch (ex: Exception) {
                println("Exception :${ex.message}")
            }
        }

    }

    fun profileApiCall() {
        progress = ProgressDialog.show(
                activity!!, null,
                "Getting view ... ", true
        )
        compositeDisposable?.add(apiService.getProfile()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleLanguageResponse, this::handleLanguageError))
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    fun handleLanguageResponse(profile: List<ProfileModel>) {
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
        if (profile != null && profile.size > 0) {
            inflateView(profile)
        }
    }

    fun handleLanguageError(error: Throwable) {
        println("Error ${error.localizedMessage}")
        if (progress != null) {
            progress?.dismiss()
            progress?.cancel()
        }
    }

    open class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val dialog = DatePickerDialog(activity!!, this, year, month, day)
            dialog.datePicker.maxDate = c.timeInMillis
            return dialog
        }

        override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
            //  btnDate.setText(ConverterDate.ConvertDate(year, month + 1, day))
            println("Convert date ${year}+${day}")
        }
    }


}