package com.assignment.deviceinventorymanagementapp.view.devicelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()
    var deviceAdded: MutableLiveData<Unit> = MutableLiveData()
    fun addDevice(name: String, inventory: String) {
        if (name.isEmpty() || inventory.isEmpty()) {
            feedback.value = "All fields are mandatory !"
            return
        }
        val deviceEntity = DeviceEntity(name, inventory.toInt(), inventory.toInt())
        viewModelScope.launch {
            repository.addDevice(deviceEntity)
            deviceAdded.postValue(Unit)
            feedback.postValue("Device successfully added.")
        }
    }

}