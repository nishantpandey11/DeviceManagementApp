package com.assignment.deviceinventorymanagementapp.view.employeelist

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.utils.Utility
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.emp_fragment.*
import javax.inject.Inject

class EmployeeFragment : Fragment(R.layout.emp_fragment) {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: EmployeeVM


    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EmployeeVM::class.java)

        viewModel.feedback.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
        viewModel.deviceAdded.observe(viewLifecycleOwner, Observer {
            findNavController().popBackStack()
        })

        btn_add_emp.setOnClickListener {
            Utility.hideKeyboard(activity)
            viewModel.addEmployee(
                empName.editText?.text.toString(),
                empEmail.editText?.text.toString()
            )
        }
    }
}