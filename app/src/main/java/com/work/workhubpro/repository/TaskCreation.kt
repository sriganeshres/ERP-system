package com.work.workhubpro.repository

import com.work.workhubpro.api.WorkHubApi
import com.work.workhubpro.models.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class TaskCreation @Inject constructor(private val workHubApi: WorkHubApi) {
    private val _id = MutableStateFlow<Int>(0)
    val id: StateFlow<Int> get() = _id.asStateFlow()

    suspend fun getTask(request: Task) {
        val response = workHubApi.createTask(request)
        println(response.body()?.name)
        if (response.isSuccessful && response.body() != null) {
//            _id.emit(response.body()!!.ID)
            println(_id.value) // Print the value of _id
            println("hello finally")
        } else {
            // Handle error or null body case
            println("Error: Response not successful or body is null")
        }
    }
}