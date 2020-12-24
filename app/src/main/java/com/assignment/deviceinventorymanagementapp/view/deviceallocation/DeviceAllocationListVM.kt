package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import androidx.lifecycle.*
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory
import com.assignment.deviceinventorymanagementapp.data.model.DeviceStatus
import com.assignment.deviceinventorymanagementapp.data.model.Result
import com.assignment.deviceinventorymanagementapp.utils.Utility
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceAllocationListVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()


    private var currentFiltering = DeviceStatus.ISSUED
    private val _forceUpdate = MutableLiveData<Boolean>(false)


    /*  init {
          setFiltering(currentFiltering)
      }*/

    private val _deviceInventoryList: LiveData<List<DeviceInventory>> =
        _forceUpdate.switchMap {
            repository.getDeviceInventoryList().switchMap { filterTasks(it) }
        }

    var deviceInventoryList: LiveData<List<DeviceInventory>> =
        Transformations.map(_deviceInventoryList) {
            filterItems(it, currentFiltering)
        }


    private fun filterTasks(tasksResult: Result<List<DeviceInventory>>): LiveData<List<DeviceInventory>> {
        val result = MutableLiveData<List<DeviceInventory>>()
        if (tasksResult is Result.Success) {
            viewModelScope.launch {
                result.value = filterItems(tasksResult.data, currentFiltering)
            }
        } else {
            result.value = emptyList()
        }

        return result
    }

    private fun filterItems(
        inventories: List<DeviceInventory>,
        filteringType: DeviceStatus
    ): List<DeviceInventory> {
        val inventoriesToShow = ArrayList<DeviceInventory>()
        for (inventory in inventories) {
            when (filteringType) {
                DeviceStatus.ISSUED -> {
                    if (inventory.status == Utility.enumToIntDeviceStatus(DeviceStatus.ISSUED))
                        inventoriesToShow.add(inventory)
                }
                DeviceStatus.LOST -> {
                    if (inventory.status == Utility.enumToIntDeviceStatus(DeviceStatus.LOST))
                        inventoriesToShow.add(inventory)
                }
                DeviceStatus.RETURNED -> {
                    if (inventory.status == Utility.enumToIntDeviceStatus(DeviceStatus.RETURNED))
                        inventoriesToShow.add(inventory)
                }
                DeviceStatus.AVAILABLE -> {
                    inventoriesToShow.add(inventory)
                }
            }
        }
        return inventoriesToShow
    }

    fun updateDeviceStatus(deviceInventory: DeviceInventory, deviceStatus: DeviceStatus) {
        var result: Int
        viewModelScope.launch {
            val deviceEntity = repository.getDeviceById(deviceInventory.deviceId)
            deviceEntity?.let {
                when (deviceStatus) {
                    DeviceStatus.RETURNED -> {
                        result = repository.updateInventoryStatus(
                            deviceInventory.recordId,
                            Utility.enumToIntDeviceStatus(DeviceStatus.RETURNED)
                        )
                        if (result > 0)
                            repository.updateAvailableInventory(
                                deviceEntity.currentAvailableInventory + 1,
                                deviceEntity.deviceId
                            )
                    }
                    DeviceStatus.LOST -> {
                        result = repository.updateInventoryStatus(
                            deviceInventory.recordId,
                            Utility.enumToIntDeviceStatus(DeviceStatus.LOST)
                        )
                        if (result > 0)
                            repository.updateTotalInventory(
                                deviceEntity.totalInventory - 1,
                                deviceEntity.deviceId
                            )
                    }
                    else -> {
                        result = -1
                    }
                }
            }
        }
    }

    fun setFiltering(requestType: DeviceStatus) {
        currentFiltering = requestType
        refreshData()
    }

    private fun refreshData() {
        _forceUpdate.value = true
    }

}