package com.assignment.deviceinventorymanagementapp.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.assignment.deviceinventorymanagementapp.R

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
}

interface DialogCallBack<T> {
    fun onPositiveButtonClicked(data: T)
    fun onNegativeButtonClicked(data: T) {}
}