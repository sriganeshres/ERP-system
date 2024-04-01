package com.work.workhubpro.ui.navigation

import com.work.workhubpro.ui.screens.bottombar.Bottombar
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
<<<<<<< Updated upstream
import com.work.workhubpro.ui.screens.community.Community
import com.work.workhubpro.ui.screens.home.Home
import com.work.workhubpro.ui.screens.profile.Profile
import com.work.workhubpro.ui.screens.projects.Projects
import com.work.workhubpro.ui.screens.signup.SignupScreen
=======
import com.work.workhubpro.ui.screens.Community
import com.work.workhubpro.ui.screens.Home
import com.work.workhubpro.ui.screens.Profile
import com.work.workhubpro.ui.screens.Projects
import com.work.workhubpro.ui.screens.SignupScreen
import com.work.workhubpro.ui.screens.Create_Org_Screen
>>>>>>> Stashed changes

@Composable
fun Navigation() {
    val navController = rememberNavController()
<<<<<<< Updated upstream
    NavHost(navController = navController, startDestination = Navscreen.Signup.route) {
        composable(route = Navscreen.Signup.route) {
            SignupScreen(navController = navController)
        }
        composable(route = Navscreen.Profile.route) {
            Profile(navController = navController)
        }
=======
    NavHost(navController = navController, startDestination = Navscreen.Create_Org.route ){
    composable(route = Navscreen.Signup.route){
     SignupScreen(navController = navController)
    }
    composable(route = Navscreen.Profile.route){
      Profile(navController = navController)
    }
>>>>>>> Stashed changes
        composable(
            route = "${Navscreen.Bottom.route}/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val argumentName = backStackEntry.arguments?.getString("name").orEmpty()
            Bottombar(argumentName, navController = navController)
        }
        composable(
            route = "${Navscreen.Home.route}/{name}",
            arguments = listOf(navArgument("name") { type = NavType.StringType })
        ) { backStackEntry ->
            val argumentName = backStackEntry.arguments?.getString("name").orEmpty()
            Home(argumentName, navController = navController)
        }

        composable(route = Navscreen.Community.route) {
            Community(navController = navController)
        }
        composable(route = Navscreen.Projects.route) {
            Projects(navController = navController)
        }

        composable(route=Navscreen.Create_Org.route){
            Create_Org_Screen(navController = navController)
        }

    }

}