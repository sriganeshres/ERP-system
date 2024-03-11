package com.work.workhubpro.ui

import Bottombar
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.work.workhubpro.WorkActivity
import com.work.workhubpro.screens.Profile
import com.work.workhubpro.screens.SignupScreen
import com.work.workhubpro.ui.Navigation.Navscreen
import com.work.workhubpro.ui.screens.Community
import com.work.workhubpro.ui.screens.Home
import com.work.workhubpro.ui.screens.Projects

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
        composable(route = Navscreen.Bottom.route) {
            Bottombar(navController = navController)
        }
        composable(route = Navscreen.Home.route) {
            Home(navController = navController)
        }
        composable(route = Navscreen.Community.route) {
            Community(navController = navController)
        }
        composable(route = Navscreen.Projects.route) {
            Projects(navController = navController)
        }

    }

}