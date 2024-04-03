package com.work.workhubpro.repository

import com.work.workhubpro.api.UserApi
import com.work.workhubpro.models.Organisation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class OrganisationCreation @Inject constructor(private val userApi : UserApi){
    private val _id = MutableStateFlow<Int>(0)
    val id: StateFlow<Int> get() = _id.asStateFlow()

    suspend fun getOrg(request: Organisation){
        val response = userApi.createorg(request)
        if (response.isSuccessful && response.body() != null) {
            _id.emit(response.body()!!.ID)
            println(_id.value) // Print the value of _id
            println("hello finally")
        } else {
            // Handle error or null body case
            println("Error: Response not successful or body is null")
        }
    }


}