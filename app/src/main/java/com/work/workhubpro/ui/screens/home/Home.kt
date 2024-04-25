package com.work.workhubpro.ui.screens.home

import android.service.autofill.OnClickAction
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import com.work.workhubpro.R
import com.work.workhubpro.SharedViewModel
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.theme.LightBlue
import com.work.workhubpro.ui.theme.Lightblue2
import com.work.workhubpro.ui.theme.mediumblue
import java.time.LocalDate

@Composable
fun Home(name: String, navController: NavController, sharedViewModel: SharedViewModel) {
    val datedialogueState = rememberMaterialDialogState()
    val viewmodel : HomeViewModel = hiltViewModel()
    val workhub = viewmodel.workhub.collectAsState().value
    var name = "technovia"
    var description = "description"
    println(workhub)
    if(workhub!=null){
        name = workhub.name
        description = workhub.description
    }

    LaunchedEffect(Unit) {
        viewmodel.getworkhub(sharedViewModel.user.value?.id.toString())
    }
    val font = FontFamily(Font(R.font.kaushanscript))
    val joseph = FontFamily(Font(R.font.josefinsansbold))


    var showDialog by remember { mutableStateOf(false) }

    // Show the dialog when the composable is first composed
    LaunchedEffect(Unit) {
        showDialog = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Lightblue2)
    ) {
        Image(
            painter = painterResource(id = R.drawable.border),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(75.dp)
        )

        Text(
            text = name,
            style = TextStyle(
                fontFamily = font,
                fontSize = 35.sp,
                color = Color.Black
            ),
            modifier = Modifier.padding(16.dp)
        )

        Surface(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
                .shadow(20.dp)
                .height(200.dp),
            color = mediumblue,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text=description,
                style = TextStyle(
                    fontFamily = joseph,
                    fontSize = 18.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(18.dp),

            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        )  {
            // Add your Text composable here
            Text(text = "Company Calender",
                style = TextStyle(
                    fontFamily = font,
                    fontSize = 30.sp,
                    color = Color.Black
                ),
                modifier = Modifier.padding(10.dp))

                Image(
                    painter = painterResource(R.drawable.calendar),
                    contentDescription = "Your Image Description",
                    modifier = Modifier
                        .size(70.dp)
                        .shadow(10.dp)
                        .padding(10.dp)
                        .clickable { datedialogueState.show() },
                )

        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Surface(
                modifier = Modifier
                    .shadow(20.dp)
                    .fillMaxWidth(0.35f)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(8.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color =
                            Color.hsl(265f, 0.55f, 0.50f) // Valid form color
                            ,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        navController.navigate(Navscreen.Createtask.route)
                    },
                ) {
                    Text(text = "Add Task",color=Color.White)
                }
            }
            Surface(
                modifier = Modifier
                    .shadow(20.dp)
                    .fillMaxWidth(0.5f)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(8.dp)


            ) {
                Button(
                    modifier = Modifier
                        .background(
                            color =
                            Color.hsl(265f, 0.55f, 0.50f) // Valid form color
                            ,
                            shape = RoundedCornerShape(10.dp)
                        ),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        navController.navigate(Navscreen.CreateProject.route)
                    },
                ) {
                    Text(text = "Add Project",color=Color.White)
                }
            }
       }
        Spacer(modifier = Modifier.height(20.dp))
        Surface(
            modifier = Modifier
                .padding(16.dp)
                .shadow(20.dp)// Add padding for the Surface
                .fillMaxWidth() ,
            shape = RoundedCornerShape(8.dp)// Ensure the Surface occupies the entire width
        ) {
            Text(
                text = "Add Employers",
                style = TextStyle(
                    fontFamily = joseph,
                    fontSize = 18.sp,
                    color = Color.Black
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .background(LightBlue)
                    .padding(10.dp)
                    .fillMaxWidth() // Make Text fill the entire width inside the Surface
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.admin_symbol),
                contentDescription = "Open Calendar",
                modifier = Modifier
                    .size(1.dp)
                    .padding(16.dp)
                    .clickable {
                        showDialog = true
                    }
            )
        }


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomEnd
        ) {

                MaterialDialog(
                    dialogState = datedialogueState,
                    buttons = {
                        LaunchedEffect(Unit) {
                            showDialog = true
                        }
                    }
                ) {
                    datepicker(
                        initialDate = LocalDate.now()
                    )

                }
        }
    }
}
@Preview
@Composable
fun HomePreview() {
    val navController = rememberNavController()
    Home(
        name = "Preview",
        navController = navController,
        sharedViewModel = SharedViewModel()
    )
}
