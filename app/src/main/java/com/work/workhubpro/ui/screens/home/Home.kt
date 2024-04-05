package com.work.workhubpro.ui.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import com.work.workhubpro.SharedViewModel
import com.work.workhubpro.models.User

@Composable
fun Home(name: String, navController: NavController,sharedViewModel: SharedViewModel) {
println(sharedViewModel.user)
    val user:User? = sharedViewModel.user.collectAsState().value
    Column {
        Text(text = "hello $name")
        Text(text ="email ${user?.email}  ")

    }

}