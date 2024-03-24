package com.work.workhubpro.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size


import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.navigation.NavController
import com.work.workhubpro.R
import com.work.workhubpro.ui.theme.LightBlue


data class User(val name: String, val email: String, val profilePicture: Int)
val josefinSansFontFamily = FontFamily(
    // Regular font style
    Font(R.font.josefinsansbold, FontWeight.Bold), // Bold font style
    // Add more font styles if available (e.g., italic, etc.)
)

@Composable
fun Profile(navController: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = LightBlue)){


        Box(modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),

            ) {
            Image(
                painter = painterResource(id = R.drawable.bg),contentDescription = "bg file",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth()

            )
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
                contentAlignment = Alignment.Center

            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo), contentDescription = "logo file",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(shape = CircleShape)
                        .size(150.dp)


                )
            }
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
            .padding(10.dp)
            .padding(
                top = 0.dp
            )
            .background(Color.White)
            .clip(RoundedCornerShape(100.dp))){

            Text(text = "Name: ",
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),

                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal
            )
            Text(text = "John",
                color = LightBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal
            )
            Text(text = "Occupation: ",
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal
            )
            Text(text = "Senior Engineer ",
                color = LightBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal
            )
            Text(text = "Department:",
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal
            )
            Text(text = "FrontEnd Dev ",
                color = LightBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal

            )
            Text(text = "Salary: ",
                color = Color.Blue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal

            )
            Text(text = "$100k",
                color = LightBlue,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp),
                fontFamily = josefinSansFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 8f.em,
                fontStyle = FontStyle.Normal

            )



        }

    }
}









