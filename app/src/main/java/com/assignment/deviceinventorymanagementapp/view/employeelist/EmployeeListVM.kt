package com.assignment.deviceinventorymanagementapp.view.employeelist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.assignment.deviceinventorymanagementapp.data.Repository
import javax.inject.Inject

class EmployeeListVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()



}