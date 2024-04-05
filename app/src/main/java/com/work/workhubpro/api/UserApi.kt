package com.work.workhubpro.api

import com.work.workhubpro.models.LoginResponse
import com.work.workhubpro.models.Organisation
import com.work.workhubpro.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject


interface UserApi {
    @POST("/api/signup")
    suspend fun signup(@Body request: User): Response<User>
    @POST("/api/login")
    suspend fun login(@Body request: User): Response<LoginResponse>

    @POST("/api/create")
    suspend fun createorg(@Body request: Organisation): Response<Organisation>

    @POST("/api/join_org")
    suspend fun joinorg(@Body request: Organisation): Response<String>
}