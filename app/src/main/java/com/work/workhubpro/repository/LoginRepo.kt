package com.work.workhubpro.repository

import com.work.workhubpro.api.UserApi
import com.work.workhubpro.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class LoginRepo @Inject constructor(private val userApi: UserApi) {
    private val user = MutableStateFlow<User?>(null)
    private val _token = MutableStateFlow<String>("")
    val token: StateFlow<String> get()= _token.asStateFlow()
    suspend fun getUser(request: User) {
        println("heroku")
        val response = userApi.login(request)
        println("AWS")
        if (response.isSuccessful && response.body() != null) {
            println("gojo")
        }
    }

}