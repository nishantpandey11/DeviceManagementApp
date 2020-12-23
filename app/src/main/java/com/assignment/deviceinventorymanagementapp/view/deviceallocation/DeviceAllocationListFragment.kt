package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.device_allotted_list_fragment.*
import javax.inject.Inject

class DeviceAllocationListFragment : Fragment(R.layout.device_allotted_list_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DeviceAllocationListVM

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DeviceAllocationListVM::class.java)

        viewModel.feedback.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        btn_allocate_device.setOnClickListener {
            val actions =
                DeviceAllocationListFragmentDirections.actionDeviceAllottedListFragmentToDeviceAllocationFragment()
            findNavController().navigate(actions)
        }
    }
}