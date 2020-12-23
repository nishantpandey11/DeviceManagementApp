package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.utils.Utility
import com.assignment.deviceinventorymanagementapp.utils.Utility.showDatePicker
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.device_allocation_fragment.*
import javax.inject.Inject

class DeviceAllocationFragment : Fragment(R.layout.device_allocation_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DeviceAllocationVM


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DeviceAllocationVM::class.java)

        viewModel.feedback.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.allotment.observe(viewLifecycleOwner, Observer {
            goBack()
        })

        viewModel.employeeNames.observe(viewLifecycleOwner, {
            initEmpAutocomplete(it)
        })

        viewModel.deviceNames.observe(viewLifecycleOwner, {
            initDeviceSpinner(it)
        })

        btn_allocate_device.setOnClickListener {
            Utility.hideKeyboard(activity)
            allocateDevice()
        }
        btn_cancel.setOnClickListener {
            goBack()
        }
        btn_date_picker.setOnClickListener {
            it.showDatePicker()
        }

    }

    private fun allocateDevice() {
        val deviceName = spinner.selectedItem?.toString()
        val employeeName = autoTextViewEmployeeName.editableText
        val returnDate = btn_date_picker.text.split(":")[1].trim()

        deviceName?.let {
            val employeeEntity = viewModel.getEmployeeEntity(employeeName.toString())
            val deviceEntity = viewModel.getDeviceEntity(deviceName)

            if (employeeEntity == null || deviceEntity == null)
                return
            viewModel.allocateDevice(deviceEntity, employeeEntity, returnDate)
        }
    }


    private fun initEmpAutocomplete(dataList: List<String>) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_list_item_activated_1,
            dataList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        autoTextViewEmployeeName.setAdapter(adapter)
        //autoTextViewEmployeeName.onItemSelectedListener = this
    }


    private fun initDeviceSpinner(dataList: List<String>) {
        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            dataList
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        //spinner.onItemSelectedListener = this
    }

    private fun goBack() {
        Utility.hideKeyboard(activity)
        findNavController().popBackStack()

    }
}