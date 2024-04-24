package com.work.workhubpro.models

import javax.inject.Inject

data class Task @Inject
constructor(
    val ID: Int = 0,
    val name: String,
    val description: String,
    val assigned_by: Int = 0,
    val assigned_to: String,
    val project_key: Int = 0,
    val work_hub_id: Int = 0,
    val status: String
) {
}

data class UpdateTask @Inject
constructor(
    val ID: Int,
    val status: String
) {
}

