package com.work.workhubpro.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.work.workhubpro.ui.screens.bottombar.Bottombar
import com.work.workhubpro.ui.screens.community.Community
import com.work.workhubpro.ui.screens.createOrg.Create_OrgScreen
import com.work.workhubpro.ui.screens.home.Home
import com.work.workhubpro.ui.screens.landing.LandingPage
import com.work.workhubpro.ui.screens.profile.Profile
import com.work.workhubpro.ui.screens.projects.Projects
import com.work.workhubpro.ui.screens.signup.SignupScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Navscreen.Landing.route ){
    composable(route = Navscreen.Signup.route){
     SignupScreen(navController = navController)
    }
    composable(route = Navscreen.Profile.route){
      Profile(navController = navController)
    }
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
            Create_OrgScreen(navController = navController)
        }
        composable (route = Navscreen.Landing.route) {
            LandingPage(navController = navController)
        }
    }
}