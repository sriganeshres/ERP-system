package com.work.workhubpro.ui.Navigation

sealed class Navscreen(val route: String) {
    object Signup : Navscreen("signup")
    object Profile : Navscreen("profile")
}