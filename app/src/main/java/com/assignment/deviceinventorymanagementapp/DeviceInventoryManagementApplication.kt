package com.assignment.deviceinventorymanagementapp

import android.app.Activity
import android.app.Application
import com.assignment.deviceinventorymanagementapp.di.component.AppComponent
import com.assignment.deviceinventorymanagementapp.di.component.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class DeviceInventoryManagementApplication : Application(), HasActivityInjector {
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .application(this)
            .build()
            .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> {
        return androidInjector
    }
}