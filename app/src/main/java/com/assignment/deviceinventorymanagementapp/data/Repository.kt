package com.assignment.deviceinventorymanagementapp.data

import androidx.lifecycle.LiveData
import com.assignment.deviceinventorymanagementapp.data.model.DeviceEntity
import com.assignment.deviceinventorymanagementapp.data.model.DeviceInventory
import com.assignment.deviceinventorymanagementapp.data.model.EmployeeEntity
import com.assignment.deviceinventorymanagementapp.data.model.Result

interface Repository {

    /**
     * To add Device information into Database*/
    suspend fun addDevice(deviceEntity: DeviceEntity)

    /**
     * Select a device by id.
     *
     */
    suspend fun getDeviceById(deviceId: Int): DeviceEntity?

    suspend fun getDeviceRById(deviceId: Int): Result<DeviceEntity>

    /**
     * To Fetch All Devices from Database*/
    fun getDeviceList(): LiveData<List<DeviceEntity>>

    /**
     * To update Device information into Database*/
    suspend fun updateDevice(deviceEntity: DeviceEntity)

    /**
     * Update a device.
     *
     * @param totalInventory Device totalInventory to be updated
     * @param deviceId Device id
     * @return the number of Devices updated. This should always be 1.
     */
    suspend fun updateTotalInventory(totalInventory: Int, deviceId: Int): Int

    /**
     * To remove device record from Database*/
    suspend fun deleteDevice(deviceId: Int): Int

    /**
     * To Fetch All Available Devices from Database*/
    fun observeAvailableDevices(): LiveData<List<DeviceEntity>>

    /**
     * To update Device Current Available information into Database*/
    suspend fun updateAvailableInventory(currentInventory: Int, deviceId: Int): Int

    /**
     *  Get Result DeviceEntity List*/
    suspend fun getDevices(): Result<List<DeviceEntity>>

    /**
     *
     * ****************************Employee Repository************************
     *
     * */

    /**
     * To add Employee information into Database*/
    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    /**
     * Get a single Employee.
     *
     * @param empId the Employee id.
     * @return the Employee with empId.
     */
    suspend fun getEmployeeById(empId: Int): EmployeeEntity?

    /**
     * To remove Employee record to Database*/
    suspend fun deleteEmployee(empId: Int): Int

    /**
     * To Fetch All Employees from Database*/
    fun getEmployeeList(): LiveData<List<EmployeeEntity>>

    /**
     *
     ***********************DeviceInventory Repository***************
     *
     * */

    /**
     * To add DeviceInventory information into Database*/
    suspend fun addDeviceInventory(deviceInventory: DeviceInventory)

    /**
     * To remove DeviceInventory record to Database*/
    suspend fun deleteDeviceInventory(recordId: Int): Int

    /**
     * To update DeviceInventory information into Database*/
    suspend fun updateDeviceInventory(deviceInventory: DeviceInventory): Int

    /**
     * To remove DeviceInventory record to Database*/
    suspend fun updateInventoryStatus(recordId: Int, status: Int): Int

    /**
     * To Fetch All DeviceInventory from Database*/
    fun getDeviceInventoryList(): LiveData<Result<List<DeviceInventory>>>

    /**
     * Get a DeviceInventory by id.
     *@param recordId for recordId
     * @return the number of DeviceInventory deleted. This should always be 1.
     */
    suspend fun getDeviceInventoryById(recordId: Int): DeviceInventory?

    /**
     * Observes list of DeviceInventory.
     * @return all DeviceInventory.
     */
    suspend fun getAllIssuedOrLostInventoryOfEmpId(empId: Int): List<DeviceInventory>
}