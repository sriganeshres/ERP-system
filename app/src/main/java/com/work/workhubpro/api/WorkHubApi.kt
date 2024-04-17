package com.work.workhubpro.api

import com.work.workhubpro.models.JoinOrganization
import com.work.workhubpro.models.Organisation
import com.work.workhubpro.models.Project
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface WorkHubApi {
    @POST("/api/workhub")
    suspend fun createorg(@Body request: Organisation): Response<Organisation>

    @POST("/api/createProject")
    suspend fun createProj(@Body request: Project): Response<Project>

    @POST("/api/join")
    suspend fun joinOrg(@Body request: JoinOrganization): Response<String>

    @GET("/api/getworkhub")
    suspend fun getWorkHub(@Query("key") request: Int): Response<String>
}