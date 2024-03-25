package com.work.workhubpro.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun Home(Name:String,navController: NavController) {
    Text(text = "hello $Name")
}