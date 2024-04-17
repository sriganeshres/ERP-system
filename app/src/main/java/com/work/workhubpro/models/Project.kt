package com.work.workhubpro.models

import com.work.workhubpro.ui.screens.profile.User
import javax.inject.Inject

data class Project @Inject constructor(
    var name: String,
    var description: String?,
    var project_leader: String?,
//    var project_key: Int,
    val members :List<User> = emptyList(),
//    var workhub_id: Int
)
