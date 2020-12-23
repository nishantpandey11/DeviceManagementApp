package com.assignment.deviceinventorymanagementapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.deviceinventorymanagementapp.di.ViewModelKey
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import com.assignment.deviceinventorymanagementapp.view.deviceallocation.DeviceAllocationListVM
import com.assignment.deviceinventorymanagementapp.view.deviceallocation.DeviceAllocationVM
import com.assignment.deviceinventorymanagementapp.view.devicelist.DeviceListVM
import com.assignment.deviceinventorymanagementapp.view.devicelist.DeviceVM
import com.assignment.deviceinventorymanagementapp.view.employeelist.EmployeeListVM
import com.assignment.deviceinventorymanagementapp.view.employeelist.EmployeeVM
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(DeviceVM::class)
    abstract fun bindDeviceVM(viewModel: DeviceVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeVM::class)
    abstract fun bindEmployeeVM(viewModel: EmployeeVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(EmployeeListVM::class)
    abstract fun bindEmployeeListVM(viewModel: EmployeeListVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceListVM::class)
    abstract fun bindDeviceListVM(viewModel: DeviceListVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceAllocationListVM::class)
    abstract fun bindDeviceAllocationListVM(viewModel: DeviceAllocationListVM): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DeviceAllocationVM::class)
    abstract fun bindDeviceAllocationVM(viewModel: DeviceAllocationVM): ViewModel
}