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
class TaskFormViewModel @Inject constructor(
    private val repo: TaskRepository
) : ViewModel() {
    fun createTask(name: String, description: String, assignedTo: Int,assigned_by: Int,workhub_id:Int,project_key:Int) {
        println(name)
            val task = Task(
                name = name,
                description = description,
                assigned_to = assignedTo,
                assigned_by = assigned_by ,
                work_hub_id = workhub_id,
                project_key = project_key,
                status = "notStarted"
            ) // Create a new Task instance
            viewModelScope.launch {
                repo.createTask(task)
            }

    }
}