package com.assignment.deviceinventorymanagementapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.deviceinventorymanagementapp.di.ViewModelKey
import com.assignment.deviceinventorymanagementapp.view.ViewModelFactory
import com.assignment.deviceinventorymanagementapp.view.devicelist.DeviceVM
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
    abstract fun bindListViewModel(viewModel: DeviceVM): ViewModel
}