package com.work.workhubpro.models

import javax.inject.Inject

data class Project @Inject
constructor(
    val name: String,
    val description : String,
    val projectLead:String,
    val members: List<User> = emptyList(),
    val ID : Int=0,
    val workHubId: Int = 0
)
