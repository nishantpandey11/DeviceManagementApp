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
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity
import com.assignment.deviceinventorymanagementapp.utils.DialogCallBack
import com.assignment.deviceinventorymanagementapp.utils.Utility.showDialog
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.employee_list_fragment.*
import javax.inject.Inject

class EmployeeListFragment : Fragment(R.layout.employee_list_fragment), EmployeeListCallback,
    DialogCallBack<EmployeeEntity> {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: EmployeeListVM
    private lateinit var adapter: EmployeeListAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EmployeeListVM::class.java)

        adapter = EmployeeListAdapter(this)
        rv_emp_list.adapter = adapter

        viewModel.feedback.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.empList.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                rv_emp_list.visibility = View.GONE
                tv_no_item.visibility = View.VISIBLE
            } else {
                rv_emp_list.visibility = View.VISIBLE
                tv_no_item.visibility = View.GONE
            }
            adapter.submitList(it)
        })

        btn_add_emp.setOnClickListener {
            val actions =
                EmployeeListFragmentDirections.actionEmployeeListFragmentToEmployeeFragment()
            findNavController().navigate(actions)
        }

    }

    override fun onDeleteClick(item: EmployeeEntity) {
        showDialog("Employee : ${item.name}", getString(R.string.alert_message), this, item)

    }

    override fun onPositiveButtonClicked(data: EmployeeEntity) {
        viewModel.deleteItem(data)
    }
}