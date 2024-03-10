package com.work.workhubpro.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.work.workhubpro.screens.Profile
import com.work.workhubpro.screens.SignupScreen
import com.work.workhubpro.ui.Navigation.Navscreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navscreen.Signup.route ){
    composable(route = Navscreen.Signup.route){
     SignupScreen(navController = navController)
    }
    composable(route = Navscreen.Profile.route){
      Profile(navController = navController)
    }
    }

}