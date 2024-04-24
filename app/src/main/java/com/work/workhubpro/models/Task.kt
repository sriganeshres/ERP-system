package com.work.workhubpro.models

import javax.inject.Inject

data class Task @Inject
constructor(
    val name: String,
    val description : String,
    val assignedto:String,
)