package com.work.workhubpro.repository

import com.work.workhubpro.api.UserApi
import com.work.workhubpro.di.NetworkModule
import com.work.workhubpro.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


class UserRepository @Inject constructor(private val userapi: UserApi) {
    private val _user = MutableStateFlow<User?>(null)
    private val _token = MutableStateFlow<String>("")

    val user: StateFlow<User?> get() = _user.asStateFlow()
    val token: StateFlow<String> get() = _token.asStateFlow()
    suspend fun getUser(request: User) {
        val response = userapi.signup(request)
        if (response.isSuccessful && response.body() != null) {
            _user.emit(response.body()!!.user)
            _token.emit(response.body()!!.token)
            println(response.body())
        }
        else{
            println("some error")
        }
    }
    suspend fun user_from_token(token : String){
        val response = userapi.token(token)
        if (response.isSuccessful && response.body() != null) {
            _user.emit(response.body())
        }
        else{
            println("some error")
        }
    }
}