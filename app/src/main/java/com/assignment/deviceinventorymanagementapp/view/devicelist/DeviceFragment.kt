package com.assignment.deviceinventorymanagementapp.view.devicelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.assignment.deviceinventorymanagementapp.R
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
        /*
     *  Remember in our FragmentModule, we
     * defined MovieListFragment injection? So we need
     * to call this method in order to inject the
     * ViewModelFactory into our Fragment
     * */
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DeviceVM::class.java)

        btn_add_device.setOnClickListener {
            viewModel.addDevice(
                deviceName.editText?.text.toString(),
                deviceCount.editText?.text.toString().toInt()
            )
        }


    }
}