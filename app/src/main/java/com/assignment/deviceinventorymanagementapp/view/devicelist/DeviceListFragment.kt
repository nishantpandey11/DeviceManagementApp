package com.assignment.deviceinventorymanagementapp.view.devicelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assignment.deviceinventorymanagementapp.R
import kotlinx.android.synthetic.main.device_list_fragment.*

class DeviceListFragment:Fragment(R.layout.device_list_fragment ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_add_device.setOnClickListener {
            val actions = DeviceListFragmentDirections.actionDeviceListFragmentToDeviceFragment()
            findNavController().navigate(actions)
        }

    }

}