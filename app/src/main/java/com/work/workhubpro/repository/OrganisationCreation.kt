package com.work.workhubpro.repository

import com.work.workhubpro.api.UserApi
import com.work.workhubpro.models.Organisation
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class OrganisationCreation @Inject constructor(private val userApi : UserApi){
    private val organisation = MutableStateFlow<Organisation?>(null)

    suspend fun getOrg(request: Organisation){
        println("heroku")
        val response = userApi.createorg(request)
        println("AWS")
        if(response.isSuccessful && response.body()!=null){
            println("hi")
        }
    }

}