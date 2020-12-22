package com.assignment.deviceinventorymanagementapp.view.devicelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceListVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()
    lateinit var deviceList: LiveData<List<DeviceEntity>>

    init {
        viewModelScope.launch {
            deviceList = repository.getDeviceList()
        }
    }

    fun deleteItem(item: DeviceEntity) = viewModelScope.launch {
        val deviceEntity = repository.getDeviceById(item.deviceId)
        deviceEntity?.let {
            if (it.totalInventory == it.currentAvailableInventory) {
                repository.deleteDevice(item.deviceId)
                feedback.postValue("${item.name} has been deleted")
            } else {
                feedback.postValue("\"${item.name}\" can't be deleted ,Please de-allocate the device and try again. ")
            }
        }
    }


}