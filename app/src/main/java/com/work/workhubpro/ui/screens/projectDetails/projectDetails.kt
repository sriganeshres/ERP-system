package com.work.workhubpro.ui.screens.projectDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.work.workhubpro.SharedViewModel

@Composable
fun ProjectDetails(name:String,navController: NavController, sharedViewModel: SharedViewModel) {
    Column {
        Text(text = "hello $name")
    }
}