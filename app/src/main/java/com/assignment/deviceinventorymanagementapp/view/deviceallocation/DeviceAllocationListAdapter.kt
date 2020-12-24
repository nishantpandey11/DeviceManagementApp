package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory
import com.assignment.deviceinventorymanagementapp.data.model.DeviceStatus
import com.assignment.deviceinventorymanagementapp.utils.Utility
import kotlinx.android.synthetic.main.inventory_list_item.view.*

class DeviceAllocationListAdapter(val callback: DeviceAllocationListCallback) :
    ListAdapter<DeviceInventory, DeviceAllocationListAdapter.AllocationViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DeviceInventory> = object :
            DiffUtil.ItemCallback<DeviceInventory>() {
            override fun areItemsTheSame(
                oldItem: DeviceInventory,
                newItem: DeviceInventory
            ): Boolean {
                return oldItem.recordId == newItem.recordId
            }

            override fun areContentsTheSame(
                oldItem: DeviceInventory,
                newItem: DeviceInventory
            ): Boolean {
                return oldItem == newItem

            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllocationViewHolder {
        return AllocationViewHolder(parent)
    }

    override fun onBindViewHolder(holder: AllocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class AllocationViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item = view

        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(
                R.layout.inventory_list_item,
                parent,
                false
            )
        )

        fun bind(device: DeviceInventory) {
            val context = item.context

            item.tv_employee_name.text =
                context.getString(R.string.emp_name) + " : " + device.empName
            item.tv_device_name.text =
                context.getString(R.string.device_name) + " : " + device.devName

            item.tv_device_status.text =
                context.getString(R.string.device_status) + " : " + Utility.intDeviceStatusToEnum(device.status)
            item.tv_device_returned_date.text =
                context.getString(R.string.device_date) + " : " + device.returnDate

            if (device.status == Utility.enumToIntDeviceStatus(DeviceStatus.ISSUED)) {
                item.cb_status_lost.visibility = View.VISIBLE
                item.cb_status_returned.visibility = View.VISIBLE
            } else {
                item.cb_status_lost.visibility = View.GONE
                item.cb_status_returned.visibility = View.GONE
            }


            item.cb_status_returned.setOnClickListener {
                callback.onReturnedClicked(device)
            }
            item.cb_status_lost.setOnClickListener {
                callback.onLostClicked(device)
            }

        }

    }


}