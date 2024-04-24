package com.work.workhubpro.ui.screens.taskform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.workhubpro.models.Task
import com.work.workhubpro.repository.TaskCreation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class TaskFormViewModel @Inject constructor(private val repo: TaskCreation) : ViewModel() {
    fun createTask(name: String, description: String,assignedTo: String ) {
        println(name)
        val task = Task(name,description,assignedTo) // Create a new Task instance
        viewModelScope.launch {
            repo.getTask(task)
        }
    }
}