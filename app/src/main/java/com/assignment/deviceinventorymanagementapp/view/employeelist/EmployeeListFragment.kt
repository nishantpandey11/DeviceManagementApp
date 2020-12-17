package com.assignment.deviceinventorymanagementapp.view.employeelist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assignment.deviceinventorymanagementapp.R
import kotlinx.android.synthetic.main.employee_list_fragment.*

class EmployeeListFragment:Fragment(R.layout.employee_list_fragment ) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_emp.setOnClickListener {
            val actions = EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeFragment()
            findNavController().navigate(actions)
        }

    }
}