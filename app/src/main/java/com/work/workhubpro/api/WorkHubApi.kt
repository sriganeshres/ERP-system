package com.work.workhubpro.api

import com.work.workhubpro.models.Organisation
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface WorkHubApi {
    @POST("/api/workhub")
    suspend fun createorg(@Body request: Organisation): Response<Organisation>

    @POST("/api/join_org")
    suspend fun joinorg(@Body request: Organisation): Response<String>
}