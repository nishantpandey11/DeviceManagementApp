package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import androidx.lifecycle.*
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory
import com.assignment.deviceinventorymanagementapp.data.model.DeviceStatus
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity
import com.assignment.deviceinventorymanagementapp.utils.Utility
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class DeviceAllocationVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()
    var allotment: MutableLiveData<Unit> = MutableLiveData()
    val devices = repository.observeAvailableDevices()
    val employees = repository.getEmployeeList()

    val employeeNames: LiveData<List<String>> = Transformations.map(employees) {
        it.map { employeeEntity ->
            employeeEntity.name
        }
    }
    val deviceNames: LiveData<List<String>> = Transformations.map(devices) {
        it.map { deviceEntity ->
            deviceEntity.name
        }
    }

    fun allocateDevice(
        deviceEntity: DeviceEntity,
        employeeEntity: EmployeeEntity,
        returnedDate: String
    ) {

        val c: Calendar = Calendar.getInstance()
        val currentDate = Date()
        println("currentDate = $currentDate")

        val rdList = returnedDate.split("/")
        if (rdList.size < 3)
            return
        c.set(rdList[2].toInt(), rdList[1].toInt(), rdList[0].toInt())
        println("updatedDate = ${c.time}")

        val status = Utility.enumToIntDeviceStatus(DeviceStatus.ISSUED)

        //isLoading.value = true

        viewModelScope.launch {
            repository.addDeviceInventory(
                DeviceInventory(
                    status,
                    currentDate,
                    c.time,
                    employeeEntity.empId,
                    deviceEntity.deviceId,
                    employeeEntity.name,
                    deviceEntity.name,
                )
            )
            repository.updateAvailableInventory(
                deviceEntity.currentAvailableInventory - 1,
                deviceEntity.deviceId
            )
            // isLoading.value = false
            allotment.postValue(Unit)
        }
    }

    fun getEmployeeEntity(employeeName: String): EmployeeEntity? {
        val empIndex = employeeNames.value?.indexOf(employeeName)
        return empIndex?.let {
            if (it == -1)
                null
            else
                employees.value?.get(it)
        }
    }

    fun getDeviceEntity(deviceName: String): DeviceEntity? {
        val devIndex = deviceNames.value?.indexOfFirst { it.equals(deviceName) }
        return devIndex?.let {
            if (it == -1)
                null
            else
                devices.value?.get(it)
        }
    }


}