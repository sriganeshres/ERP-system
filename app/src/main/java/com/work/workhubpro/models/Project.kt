package com.work.workhubpro.models

import javax.inject.Inject

data class Project @Inject
constructor(
    val name: String,
    val description : String,
    val project_leader:String,
    val workHub_id: Int,
    val Members: List<User>? = null,
    val ID : Int=0,
    val project_key: Int=0
)
