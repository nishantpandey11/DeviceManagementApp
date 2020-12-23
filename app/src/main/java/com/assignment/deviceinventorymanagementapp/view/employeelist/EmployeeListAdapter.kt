package com.assignment.deviceinventorymanagementapp.view.employeelist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.assignment.deviceinventorymanagementapp.R
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity
import kotlinx.android.synthetic.main.employee_list_item.view.*

class EmployeeListAdapter(val callback: EmployeeListCallback) :
    ListAdapter<EmployeeEntity, EmployeeListAdapter.EmployeeViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<EmployeeEntity> = object :
            DiffUtil.ItemCallback<EmployeeEntity>() {
            override fun areItemsTheSame(
                oldItem: EmployeeEntity,
                newItem: EmployeeEntity
            ): Boolean {
                return oldItem.empId == newItem.empId
            }

            override fun areContentsTheSame(
                oldItem: EmployeeEntity,
                newItem: EmployeeEntity
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder {
        return EmployeeViewHolder(parent)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int) {
        val emp = getItem(position)
        holder.bind(emp)
    }

    inner class EmployeeViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val item = view

        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(
                R.layout.employee_list_item,
                parent,
                false
            )
        )

        fun bind(emp: EmployeeEntity) {
            val context = item.context
            item.tv_emp_name.text = context.getString(R.string.emp_name) +" : "+ emp.name
            item.tv_emp_email.text = context.getString(R.string.emp_email) +" : "+ emp.empEmail

            item.iv_delete_row.setOnClickListener {
                callback.onDeleteClick(emp)
            }

        }

    }


}