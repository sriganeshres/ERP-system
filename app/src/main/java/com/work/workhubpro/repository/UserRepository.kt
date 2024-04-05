package com.work.workhubpro.repository

import com.work.workhubpro.api.UserApi
import com.work.workhubpro.di.NetworkModule
import com.work.workhubpro.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


class UserRepository @Inject constructor(private val userapi: UserApi) {
    private val user = MutableStateFlow<User?>(null)

    suspend fun getUser(request: User) {
        println("heroku")
        val serviceNumber = 1
        println(request)
        val response = userapi.signup(request)

        if (response.isSuccessful && response.body() != null) {
            println("gojo")
        }
    }

}