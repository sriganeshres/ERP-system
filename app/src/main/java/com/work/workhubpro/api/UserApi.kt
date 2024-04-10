package com.work.workhubpro.api

import com.work.workhubpro.models.LoginResponse
import com.work.workhubpro.models.Organisation
import com.work.workhubpro.models.SignupResponse
import com.work.workhubpro.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import javax.inject.Inject
import javax.inject.Named


interface UserApi {
    @POST("/api/signup")
    suspend fun signup(@Body request: User): Response<SignupResponse>

    @POST("/api/login")
    suspend fun login(@Body request: User): Response<String>

    @POST("/api/token")
    suspend fun token(@Body request: String): Response<User>
}
