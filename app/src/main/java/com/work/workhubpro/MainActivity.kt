package com.work.workhubpro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.navigation.Navigation
import com.work.workhubpro.ui.theme.WorkhubProTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkhubProTheme {
                val navController = rememberNavController()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Navigation()
                    navController.addOnDestinationChangedListener { controller, destination, arguments ->
                        if (destination.route == Navscreen.Work.route) {
                            val intent = Intent(this, WorkActivity::class.java)
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }
}

