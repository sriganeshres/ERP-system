package com.work.workhubpro.repository

import com.work.workhubpro.api.WorkHubApi
import com.work.workhubpro.models.Organisation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class OrganisationCreation @Inject constructor(private val workHubApi : WorkHubApi){
    private val _id = MutableStateFlow<Int>(0)
    val id: StateFlow<Int> get() = _id.asStateFlow()
    suspend fun getOrg(request: Organisation){
        val response = workHubApi.createorg(request)
        println(response.body()?.name)
        if (response.isSuccessful && response.body() != null) {
            response.body()!!.ID?.let { _id.emit(it) }
            println(_id.value) // Print the value of _id
            println("hello finally")
        } else {
            // Handle error or null body case
            println("Error: Response not successful or body is null")
        }
    }


}