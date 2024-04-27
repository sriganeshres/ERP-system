package com.work.workhubpro.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.workhubpro.models.Project
import com.work.workhubpro.repository.OrganisationCreation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel @Inject constructor(private val repo:OrganisationCreation):ViewModel() {
    val workhub = repo.workhub
     fun getworkhub(id:String) {
         // Create a new User instance
        viewModelScope.launch {
            repo.getworkhub(id)
        }
    }
}