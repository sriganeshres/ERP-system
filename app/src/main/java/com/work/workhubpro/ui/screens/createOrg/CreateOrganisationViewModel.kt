package com.work.workhubpro.ui.screens.CreateOrg

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.workhubpro.models.Organisation
import com.work.workhubpro.repository.OrganisationCreation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateOrganisationViewModel @Inject constructor(private val repo: OrganisationCreation) : ViewModel() {


    fun createOrg(companyName: String, companyEmail: String, adminName:String,domainName:String,companyType:String) {
//        println(username)
        val newOrg = Organisation(companyName, companyEmail , adminName ,domainName,companyType) // Create a new User instance
        viewModelScope.launch {
            repo.getOrg(newOrg)
        }
    }
}
