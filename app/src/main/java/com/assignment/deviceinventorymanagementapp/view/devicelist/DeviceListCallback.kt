package com.assignment.deviceinventorymanagementapp.view.devicelist

import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity


interface DeviceListCallback {
    fun onDeleteClick(item: DeviceEntity)
}