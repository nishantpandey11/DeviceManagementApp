package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory


interface DeviceAllocationListCallback {
    fun onReturnedClicked(inventory: DeviceInventory)
    fun onLostClicked(inventory: DeviceInventory)
}