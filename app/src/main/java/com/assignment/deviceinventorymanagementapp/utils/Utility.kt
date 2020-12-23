package com.assignment.deviceinventorymanagementapp.utils

import android.app.Activity
import android.app.DatePickerDialog
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.data.model.DeviceStatus
import java.util.*

object Utility {
    fun hideKeyboard(activity: FragmentActivity?) {
        val imm: InputMethodManager =
            activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun View.showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this.context, DatePickerDialog.OnDateSetListener
            { view, yearI, monthOfYear, dayOfMonth ->
                when (this) {
                    is Button -> this.text =
                        StringBuffer("Return Date: $dayOfMonth/$monthOfYear/$yearI")
                    is TextView -> this.text =
                        StringBuffer("Return Date: $dayOfMonth/$monthOfYear/$yearI")
                    else -> throw Exception("Text Property not supported ")

                }
            },
            year, month, day
        )

        dpd.datePicker.minDate = System.currentTimeMillis() + 86400000//1hr
        dpd.show()
    }

    fun <T> Fragment.showDialog(
        title: String, message: String,
        dialogCallBack: DialogCallBack<T>?,
        data: T
    ) {
        val builder =
            AlertDialog.Builder(requireContext(), R.style.ThemeOverlay_AppCompat_Dialog_Alert)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        builder.setPositiveButton("Yes") { dialogInterface, which ->
            dialogCallBack?.onPositiveButtonClicked(data)
        }
        builder.setNegativeButton("No") { dialogInterface, which ->
            dialogCallBack?.onNegativeButtonClicked(data)
        }
        val alertDialog: AlertDialog = builder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    fun enumToIntDeviceStatus(deviceStatus: DeviceStatus) =
        when (deviceStatus) {
            DeviceStatus.AVAILABLE -> 1
            DeviceStatus.ISSUED -> 2
            DeviceStatus.RETURNED -> 3
            DeviceStatus.LOST -> 4
        }

    fun intDeviceStatusToEnum(status: Int) =
        when (status) {
            1 -> DeviceStatus.AVAILABLE
            2 -> DeviceStatus.ISSUED
            3 -> DeviceStatus.RETURNED
            4 -> DeviceStatus.LOST
            else -> DeviceStatus.ISSUED
        }
}


interface DialogCallBack<T> {
    fun onPositiveButtonClicked(data: T)
    fun onNegativeButtonClicked(data: T) {}
}