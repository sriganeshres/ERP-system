package com.work.workhubpro.ui.screens.CreateOrg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.workhubpro.models.Organisation
import com.work.workhubpro.models.User
import com.work.workhubpro.repository.OrganisationCreation
import com.work.workhubpro.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrganisationViewModel @Inject constructor(private val repo: OrganisationCreation,private val repo2: UserRepository ) : ViewModel() {

   val id = repo.id
    fun createOrg(companyName: String, description: String,admin: String, domainName: String) {
//        println(username)
        val newOrg =
            Organisation(companyName, description, admin, domainName) // Create a new User instance
        viewModelScope.launch {
            repo.getOrg(newOrg)
        }
    }

    fun signupUser(username: String, email: String, password: String,id:Int) {
        println(username)
        println("hellovabhijan")
        println(id)

        val newUser = User(username, email, password,id) // Create a new User instance
        viewModelScope.launch {
            repo2.getUser(newUser)
        }
    }
}
