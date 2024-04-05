package com.work.workhubpro.ui.screens.signup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.workhubpro.models.User
import com.work.workhubpro.repository.LoginRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class LoginPageViewModel @Inject constructor(private val repo: LoginRepo) : ViewModel() {
    fun loginuser(username: String, email: String, password: String) {
        println(username)
        val newUser = User(username, email, password) // Create a new User instance
        viewModelScope.launch {
            repo.getUser(newUser)
        }
    }
}
