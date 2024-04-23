package com.work.workhubpro.ui.screens.projectform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.workhubpro.models.Project
import com.work.workhubpro.repository.Projectcreation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class ProjectFormmViewMoel @Inject constructor(private val repo: Projectcreation) : ViewModel() {
    fun createProject(name: String, description: String, projectlead: String) {
        println(name)
        val project = Project(name, description,projectlead) // Create a new User instance
        viewModelScope.launch {
            repo.getProject(project)
        }
    }
}