package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory
import com.assignment.deviceinventorymanagementapp.data.model.DeviceStatus
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.device_allotted_list_fragment.*
import javax.inject.Inject

class DeviceAllocationListFragment : Fragment(R.layout.device_allotted_list_fragment),
    DeviceAllocationListCallback {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: DeviceAllocationListVM
    private lateinit var adapter: DeviceAllocationListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true);
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.filter_inventory, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_all_filter -> {
                viewModel.setFiltering(DeviceStatus.AVAILABLE)
                return true
            }
            R.id.menu_issued_filter -> {
                viewModel.setFiltering(DeviceStatus.ISSUED)
                return true
            }
            R.id.menu_returned_filter -> {
                viewModel.setFiltering(DeviceStatus.RETURNED)
                return true
            }
            R.id.menu_lost_filter -> {
                viewModel.setFiltering(DeviceStatus.LOST)
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(DeviceAllocationListVM::class.java)

        adapter = DeviceAllocationListAdapter(this)
        rv_allocation_list.adapter = adapter

        viewModel.feedback.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.deviceInventoryList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                rv_allocation_list.visibility = View.GONE
                tv_no_allocation.visibility = View.VISIBLE
            } else {
                rv_allocation_list.visibility = View.VISIBLE
                tv_no_allocation.visibility = View.GONE
            }
            adapter.submitList(it)
        })

        btn_allocate_device.setOnClickListener {
            val actions =
                DeviceAllocationListFragmentDirections.actionDeviceAllottedListFragmentToDeviceAllocationFragment()
            findNavController().navigate(actions)
        }
    }

    override fun onReturnedClicked(inventory: DeviceInventory) {
        viewModel.updateDeviceStatus(inventory, DeviceStatus.RETURNED)

    }

    override fun onLostClicked(inventory: DeviceInventory) {
        viewModel.updateDeviceStatus(inventory, DeviceStatus.LOST)
    }
}