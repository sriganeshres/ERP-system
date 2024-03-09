package com.work.workhubpro.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.work.workhubpro.screens.SignupScreen

@Composable
fun PostOfficeApp(){
    Surface (
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        SignupScreen()
    }
}