package com.work.workhubpro.ui.screens.taskform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.work.workhubpro.SharedViewModel
import com.work.workhubpro.models.Task
import com.work.workhubpro.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
public class TaskFormViewModel @Inject constructor(
    private val repo: TaskRepository,
    private val sharedViewModel: SharedViewModel
) : ViewModel() {
    fun createTask(name: String, description: String, assignedTo: String) {
        println(name)
        if (sharedViewModel.user.value != null) {
            val task = Task(
                name = name,
                description = description,
                assigned_to = assignedTo,
                assigned_by = sharedViewModel.user.value!!.ID!!,
                status = "notStarted"
            ) // Create a new Task instance
            viewModelScope.launch {
                repo.createTask(task)
            }
        }
    }
}