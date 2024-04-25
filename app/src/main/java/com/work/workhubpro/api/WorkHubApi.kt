package com.work.workhubpro.api

import com.work.workhubpro.models.JoinOrganization
import com.work.workhubpro.models.Organisation
import com.work.workhubpro.models.Project
import com.work.workhubpro.models.Resp
import com.work.workhubpro.models.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    @POST("api/createproject")
    suspend fun createProject(@Body request: Project): Response<Resp>

    @POST("api/createtask")
    suspend fun createTask(@Body request: Task): Response<Task>
    @GET("/api/Projects/{id}")
    suspend fun getallprojects(@Path("id") id: String): Response<List<Project>>
    @GET("/api/getworkhub/{id}")
    suspend fun getworkhub(@Path("id") id: String): Response<Organisation>

}