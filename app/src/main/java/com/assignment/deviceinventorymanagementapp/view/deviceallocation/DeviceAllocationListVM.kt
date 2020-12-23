package com.assignment.deviceinventorymanagementapp.view.deviceallocation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.deviceinventorymanagementapp.data.Repository
import com.assignment.deviceinventorymanagementapp.data.model.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceAllocationListVM @Inject constructor(val repository: Repository) : ViewModel() {
    var feedback: MutableLiveData<String> = MutableLiveData()


    init {
        viewModelScope.launch {
            var allocatedDevice = repository.getDeviceInventoryList()
            Log.e("allocation", "" + allocatedDevice.toString())

        }
    }
}