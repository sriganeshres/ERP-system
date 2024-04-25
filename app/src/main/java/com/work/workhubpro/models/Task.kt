package com.work.workhubpro.models

import javax.inject.Inject

data class Task @Inject
constructor(
    val name: String,
    val description : String,
    val assigned_to:String,
    val work_hub_id:String,
    val assigned_by: String,
    val project_key:String,
    val status:String,
)