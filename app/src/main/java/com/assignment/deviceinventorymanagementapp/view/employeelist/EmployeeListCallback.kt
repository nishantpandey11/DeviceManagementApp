package com.assignment.deviceinventorymanagementapp.view.employeelist

import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity


interface EmployeeListCallback {
    fun onDeleteClick(item: EmployeeEntity)
}