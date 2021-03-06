package com.assignment.deviceinventorymanagementapp.data

import androidx.lifecycle.LiveData
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity
import com.assignment.deviceinventorymanagementapp.data.model.Result
import com.assignment.deviceinventorymanagementapp.data.source.local.InventoryDataSource
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val inventoryDataSource: InventoryDataSource) :
    Repository {

    /**
     * To add Device information into Database
     * @param deviceEntity*/
    override suspend fun addDevice(deviceEntity: DeviceEntity) {
        inventoryDataSource.addDevice(deviceEntity)
    }

    /**
     * Select a device by id.
     *
     */
    override suspend fun getDeviceById(deviceId: Int): DeviceEntity? {
        return inventoryDataSource.getDeviceById(deviceId)
    }

    override suspend fun getDeviceRById(deviceId: Int): Result<DeviceEntity> {
        return inventoryDataSource.getDeviceRById(deviceId)
    }

    /**
     * To Fetch All Devices from Database*/
    override fun getDeviceList(): LiveData<List<DeviceEntity>> {
        return inventoryDataSource.getDeviceList()
    }

    /**
     * To update Device information into Database*/
    override suspend fun updateDevice(deviceEntity: DeviceEntity) {
        inventoryDataSource.updateDevice(deviceEntity)
    }

    /**
     * Update a device.
     *
     * @param totalInventory Device totalInventory to be updated
     * @param deviceId Device id
     * @return the number of Devices updated. This should always be 1.
     */
    override suspend fun updateTotalInventory(totalInventory: Int, deviceId: Int): Int {
        return inventoryDataSource.updateTotalInventory(totalInventory, deviceId)
    }

    /**
     * To remove device record from Database
     * @param deviceId deviceId*/
    override suspend fun deleteDevice(deviceId: Int): Int {
        return inventoryDataSource.deleteDevice(deviceId)
    }

    /**
     * To Fetch All Available Devices from Database*/
    override fun observeAvailableDevices(): LiveData<List<DeviceEntity>> {
        return inventoryDataSource.observeAvailableDevices()
    }

    /**
     * To update Device Current Available information into Database*/
    override suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int): Int {
        return inventoryDataSource.updateAvailableInventory(currentInventory, deviceId)
    }

    /**
     *  Get Result DeviceEntity List*/
    override suspend fun getDevices(): Result<List<DeviceEntity>> {
        return inventoryDataSource.getDevices()
    }

    /**
     * To Fetch All Employees from Database*/
    override fun getEmployeeList(): LiveData<List<EmployeeEntity>> {
        return inventoryDataSource.getEmployeeList()
    }

    /**
     * To add Employee information into Database
     * @param employeeEntity*/
    override suspend fun addEmployee(employeeEntity: EmployeeEntity) {
        inventoryDataSource.addEmployee(employeeEntity)
    }

    /**
     * Get a single Employee.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    override suspend fun getEmployeeById(empId: Int): EmployeeEntity? {
        return inventoryDataSource.getEmployeeById(empId)
    }

    /**
     * To remove Employee record to Database*/
    override suspend fun deleteEmployee(empId: Int): Int {
        return inventoryDataSource.deleteEmployee(empId)
    }

    /**
     * To remove DeviceInventory record to Database*/
    override suspend fun deleteDeviceInventory(recordId: Int): Int {
        return inventoryDataSource.deleteDeviceInventory(recordId)
    }

    /**
     * To update DeviceInventory information into Database*/
    override suspend fun updateDeviceInventory(deviceInventory: DeviceInventory): Int {
        return inventoryDataSource.updateDeviceInventory(deviceInventory)
    }

    /**
     * To remove DeviceInventory record to Database*/
    override suspend fun updateInventoryStatus(recordId: Int, status: Int): Int {
        return inventoryDataSource.updateInventoryStatus(recordId, status)
    }

    /**
     * To add DeviceInventory information into Database
     * @param deviceInventory*/
    override suspend fun addDeviceInventory(deviceInventory: DeviceInventory) {
        inventoryDataSource.addDeviceInventory(deviceInventory)
    }

    /**
     * To Fetch All DeviceInventories from Database*/
    override fun getDeviceInventoryList(): LiveData<Result<List<DeviceInventory>>> {
        return inventoryDataSource.getDeviceInventoryList()
    }

    /**
     * Get a DeviceInventory by id.
     *@param recordId for recordId
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    override suspend fun getDeviceInventoryById(recordId: Int): DeviceInventory? {
        return inventoryDataSource.getDeviceInventoryById(recordId)
    }

    /**
     * Observes list of DeviceInventory.
     * @return all DeviceInventory.
     */
    override suspend fun getAllIssuedOrLostInventoryOfEmpId(empId: Int): List<DeviceInventory> {
        return inventoryDataSource.getAllIssuedOrLostInventoryOfEmpId(empId)
    }

}