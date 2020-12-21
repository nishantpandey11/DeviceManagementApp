package com.assignment.deviceinventorymanagementapp.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.assignment.deviceinventorymanagementapp.DeviceInventoryManagementApplication
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.RepositoryImpl
import com.assignment.deviceinventorymanagementapp.data.source.local.InventoryDao
import com.assignment.deviceinventorymanagementapp.data.source.local.InventoryDataSource
import com.assignment.deviceinventorymanagementapp.data.source.local.InventoryDatabase
import com.assignment.deviceinventorymanagementapp.data.source.local.LocalInventoryRepository
import com.assignment.deviceinventorymanagementapp.utils.DB_NAME

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideApplication(): DeviceInventoryManagementApplication =
        DeviceInventoryManagementApplication()

    @Provides
    fun provideContext(application: Application): Context = application

    @Provides
    @Singleton
    fun provideDatabase(applicationContext: Application): InventoryDatabase {
        return Room.databaseBuilder(
            applicationContext, InventoryDatabase::class.java, DB_NAME
        )
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideDao(db: InventoryDatabase): InventoryDao = db.inventoryDao()

    @Provides
    fun provideRepository(inventoryDataSource: InventoryDataSource): Repository =
        RepositoryImpl(inventoryDataSource)

    @Provides
    fun provideLocalRepository(inventoryDao: InventoryDao): InventoryDataSource =
        LocalInventoryRepository(inventoryDao)

}