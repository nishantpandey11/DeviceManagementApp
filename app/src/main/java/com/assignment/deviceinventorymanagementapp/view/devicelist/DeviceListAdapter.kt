package com.assignment.deviceinventorymanagementapp.view.devicelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import kotlinx.android.synthetic.main.device_list_item.view.*

class DeviceListAdapter(val callback: DeviceListCallback) :
    ListAdapter<DeviceEntity, DeviceListAdapter.DeviceViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DeviceEntity> = object :
            DiffUtil.ItemCallback<DeviceEntity>() {
            override fun areItemsTheSame(oldItem: DeviceEntity, newItem: DeviceEntity): Boolean {
                return oldItem.deviceId == newItem.deviceId
            }

            override fun areContentsTheSame(oldItem: DeviceEntity, newItem: DeviceEntity): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        return DeviceViewHolder(parent)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device: DeviceEntity = getItem(position)
        holder.bind(device)
    }

    inner class DeviceViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item = view

        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(
                R.layout.device_list_item,
                parent,
                false
            )
        )

        fun bind(device: DeviceEntity) {
            val context = item.context
            item.tv_name.text = context.getString(R.string.device_name) + " : " + device.name
            item.tv_total_inventory.text =
                context.getString(R.string.total_inventory) + " : " + device.totalInventory.toString()
            item.tv_current_inventory.text =
                context.getString(R.string.current_inventory) + " : " + device.currentAvailableInventory.toString()
            item.iv_delete_row.setOnClickListener {
                callback.onDeleteClick(device)
            }

        }

    }
}