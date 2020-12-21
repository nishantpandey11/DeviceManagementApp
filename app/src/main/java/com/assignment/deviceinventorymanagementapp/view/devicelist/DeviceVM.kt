package com.assignment.deviceinventorymanagementapp.view.devicelist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceVM @Inject constructor(val repository: Repository) : ViewModel() {
    fun addDevice(name: String, inventory: Int) {
        val deviceEntity = DeviceEntity(name, inventory, inventory)
        viewModelScope.launch {
            repository.addDevice(deviceEntity)
        }
    }

}