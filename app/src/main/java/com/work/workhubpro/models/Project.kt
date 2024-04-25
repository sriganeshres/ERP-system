package com.work.workhubpro.models

import javax.inject.Inject

data class Project @Inject
constructor(
    val name: String,
    val description : String,
    val workHub_id: Int? = 0,
    val Members: List<User>? = null,
    val projectLead:String,
    val employees:List<String> = emptyList(),
    val ID : Int?=0,
    val project_key: Int=0
)
