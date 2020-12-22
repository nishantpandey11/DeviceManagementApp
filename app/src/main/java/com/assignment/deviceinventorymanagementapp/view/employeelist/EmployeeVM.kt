package com.assignment.deviceinventorymanagementapp.view.employeelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmployeeVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()
    var deviceAdded: MutableLiveData<Unit> = MutableLiveData()
    fun addEmployee(name: String, email: String) {
        if (name.isEmpty() || email.isEmpty()) {
            feedback.value = "All fields are mandatory !"
            return
        }
        val empEntity = EmployeeEntity(name, email)
        viewModelScope.launch {
            repository.addEmployee(empEntity)
            deviceAdded.postValue(Unit)
            feedback.postValue("Employee successfully added.")
        }
    }

}