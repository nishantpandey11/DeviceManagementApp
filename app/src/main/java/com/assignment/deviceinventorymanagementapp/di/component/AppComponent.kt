package com.assignment.deviceinventorymanagementapp.di.component

import android.app.Application
import com.assignment.deviceinventorymanagementapp.DeviceInventoryManagementApplication
import com.assignment.deviceinventorymanagementapp.di.module.ActivityModule
import com.assignment.deviceinventorymanagementapp.di.module.AppModule
import com.assignment.deviceinventorymanagementapp.di.module.FragmentModule
import com.assignment.deviceinventorymanagementapp.di.module.ViewModelFactoryModule
import com.assignment.deviceinventorymanagementapp.view.MainActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        AppModule::class,
        ActivityModule::class,
        FragmentModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent {
    /*fun inject(app: DeliveryDetailActivity)
    fun inject(app: DeliveryListActivity)*/
    fun inject(app: MainActivity)
    fun inject(app: DeviceInventoryManagementApplication)


    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

}
