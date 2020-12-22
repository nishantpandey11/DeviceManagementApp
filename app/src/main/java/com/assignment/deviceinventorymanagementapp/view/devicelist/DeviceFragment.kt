package com.assignment.deviceinventorymanagementapp.view.devicelist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.utils.Utility
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.device_fragment.*
import javax.inject.Inject

class DeviceFragment : Fragment(R.layout.device_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DeviceVM


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DeviceVM::class.java)

        viewModel.feedback.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.deviceAdded.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })

        btn_add_device.setOnClickListener {
            Utility.hideKeyboard(activity)
            viewModel.addDevice(
                deviceName.editText?.text.toString(),
                deviceCount.editText?.text.toString()
            )
        }
    }
}