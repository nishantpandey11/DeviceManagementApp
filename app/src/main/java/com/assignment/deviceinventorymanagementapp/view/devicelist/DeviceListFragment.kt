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
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import com.assignment.deviceinventorymanagementapp.utils.DialogCallBack
import com.assignment.deviceinventorymanagementapp.utils.Utility.showDialog
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.device_list_fragment.*
import javax.inject.Inject

class DeviceListFragment : Fragment(R.layout.device_list_fragment), DeviceListCallback,
    DialogCallBack<DeviceEntity> {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DeviceListVM
    private lateinit var adapter: DeviceListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DeviceListVM::class.java)

        adapter = DeviceListAdapter(this)
        rv_device_list.adapter = adapter

        viewModel.feedback.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.deviceList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                rv_device_list.visibility = View.GONE
                txt_no_item.visibility = View.VISIBLE
            } else {
                rv_device_list.visibility = View.VISIBLE
                txt_no_item.visibility = View.GONE
            }
            adapter.submitList(it)
        })

        btn_add_device.setOnClickListener {
            val actions = DeviceListFragmentDirections.actionDeviceListFragmentToDeviceFragment()
            findNavController().navigate(actions)
        }

    }

    override fun onDeleteClick(item: DeviceEntity) {
        showDialog("Device : ${item.name}", getString(R.string.alert_message), this, item)
    }

    override fun onPositiveButtonClicked(data: DeviceEntity) {
        viewModel.deleteItem(data)
    }

}