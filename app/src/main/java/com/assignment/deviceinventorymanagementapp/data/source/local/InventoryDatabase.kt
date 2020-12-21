package com.assignment.deviceinventorymanagementapp.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity


/**
 * The Room Database that contains the Device and Employee table.
 *
 * Note that exportSchema should be true in production databases.
 */
@Database(
    entities = [DeviceEntity::class, EmployeeEntity::class, DeviceInventory::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class InventoryDatabase : RoomDatabase() {
    abstract fun inventoryDao(): InventoryDao
}