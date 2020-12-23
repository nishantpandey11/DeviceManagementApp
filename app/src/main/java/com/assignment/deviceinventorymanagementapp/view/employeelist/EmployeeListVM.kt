package com.assignment.deviceinventorymanagementapp.view.employeelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeListVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()
    lateinit var empList: LiveData<List<EmployeeEntity>>

    init {
        viewModelScope.launch {
            empList = repository.getEmployeeList()
        }
    }

    fun deleteItem(item: EmployeeEntity) = viewModelScope.launch {
        val deviceInventories = repository.getAllIssuedOrLostInventoryOfEmpId(item.empId)
        if (deviceInventories.isEmpty()) {
            repository.deleteEmployee(item.empId)
            feedback.postValue("${item.name} has been deleted")
        } else
            feedback.postValue("\" ${item.name} \" can't be deleted, Please return the device first.")
    }


}