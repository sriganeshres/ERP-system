package com.work.workhubpro.ui.navigation

sealed class Navscreen(val route: String) {
    object Signup : Navscreen("signup")
    object Profile : Navscreen("profile")
    object Bottom : Navscreen("bottom")
    object Home : Navscreen("home")
    object Community : Navscreen("community")
    object Work : Navscreen("work")
    object Projects : Navscreen("projects")
}