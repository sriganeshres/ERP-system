package com.work.workhubpro.api

import com.work.workhubpro.models.Organisation
import com.work.workhubpro.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface UserApi {
    @POST("/api/signup")
    suspend fun signup(@Body request: User): Response<String>

    @POST("/api/create_org")
    suspend fun createorg(@Body request: Organisation): Response<String>

    @POST("/api/join_org")
    suspend fun joinorg(@Body request: Organisation): Response<String>
}