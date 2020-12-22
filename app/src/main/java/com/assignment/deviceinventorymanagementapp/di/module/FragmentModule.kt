package com.assignment.deviceinventorymanagementapp.di.module

import com.assignment.deviceinventorymanagementapp.view.devicelist.DeviceFragment
import com.assignment.deviceinventorymanagementapp.view.devicelist.DeviceListFragment
import com.assignment.deviceinventorymanagementapp.view.employeelist.EmployeeFragment
import com.assignment.deviceinventorymanagementapp.view.employeelist.EmployeeListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    /*
    * We define the name of the Fragment we are going
    * to inject the ViewModelFactory into. i.e. in our case
    * The name of the fragment: AddEditDeviceFragment
    */
    @ContributesAndroidInjector
    abstract fun provideDeviceFragment(): DeviceFragment

    @ContributesAndroidInjector
    abstract fun provideEmployeeFragment(): EmployeeFragment

    @ContributesAndroidInjector
    abstract fun provideEmployeeListFragment(): EmployeeListFragment

    @ContributesAndroidInjector
    abstract fun provideDeviceListFragment(): DeviceListFragment

}