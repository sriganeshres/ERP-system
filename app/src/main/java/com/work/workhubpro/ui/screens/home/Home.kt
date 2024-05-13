package com.work.workhubpro.ui.screens.home

import android.annotation.SuppressLint
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
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
//import com.work.workhubpro.R
import com.work.workhubpro.SharedViewModel
import com.work.workhubpro.models.Task
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.theme.Lightblue2
import com.work.workhubpro.ui.theme.mediumblue
import java.time.LocalDate


// val dummyTasks = listOf(
//     Task(
//         ID = 1,
//         name = "Implement feature X",
//         description = "Implement the new feature X in the application",
//         assigned_by = 1,
//         deadline = "2023-06-30",
//         assigned_to = "John Doe",
//         project_key = 1,
//         work_hub_id = 1,
//         status = "In Progress"
//     ),
//     Task(
//         ID = 2,
//         name = "Fix bug Y",
//         description = "Fix the bug Y in the existing codebase",
//         assigned_by = 2,
//         deadline = "2023-05-15",
//         assigned_to = "Jane Smith",
//         project_key = 2,
//         work_hub_id = 1,
//         status = "Pending"
//     ),
//     Task(
//         ID = 3,
//         name = "Refactor module Z",
//         description = "Refactor the module Z for better performance",
//         assigned_by = 1,
//         deadline = "2023-07-10",
//         assigned_to = "Michael Johnson",
//         project_key = 1,
//         work_hub_id = 2,
//         status = "In Progress"
//     ),
//     Task(
//         ID = 4,
//         name = "Implement authentication",
//         description = "Implement authentication system for the application",
//         assigned_by = 3,
//         deadline = "2023-06-20",
//         assigned_to = "Emily Davis",
//         project_key = 3,
//         work_hub_id = 2,
//         status = "Pending"
//     ),
//     Task(
//         ID = 5,
//         name = "Design UI/UX",
//         description = "Design the UI/UX for the new feature",
//         assigned_by = 2,
//         deadline = "2023-05-25",
//         assigned_to = "David Wilson",
//         project_key = 2,
//         work_hub_id = 1,
//         status = "Completed"
//     ),
//     Task(
//         ID = 6,
//         name = "Integrate payment gateway",
//         description = "Integrate the payment gateway with the application",
//         assigned_by = 1,
//         deadline = "2023-08-01",
//         assigned_to = "Sarah Thompson",
//         project_key = 1,
//         work_hub_id = 3,
//         status = "In Progress"
//     ),
//     Task(
//         ID = 7,
//         name = "Conduct user testing",
//         description = "Conduct user testing for the new feature",
//         assigned_by = 3,
//         deadline = "2023-07-15",
//         assigned_to = "Robert Anderson",
//         project_key = 3,
//         work_hub_id = 2,
//         status = "Pending"
//     ),
//     Task(
//         ID = 8,
//         name = "Optimize database queries",
//         description = "Optimize database queries for better performance",
//         assigned_by = 2,
//         deadline = "2023-06-10",
//         assigned_to = "Jessica Taylor",
//         project_key = 2,
//         work_hub_id = 1,
//         status = "In Progress"
//     ),
//     Task(
//         ID = 9,
//         name = "Implement push notifications",
//         description = "Implement push notifications for the mobile app",
//         assigned_by = 1,
//         deadline = "2023-07-20",
//         assigned_to = "Christopher Brown",
//         project_key = 1,
//         work_hub_id = 3,
//         status = "Pending"
//     ),
//     Task(
//         ID = 10,
//         name = "Deploy to production",
//         description = "Deploy the application to the production environment",
//         assigned_by = 3,
//         deadline = "2023-08-10",
//         assigned_to = "Ashley Garcia",
//         project_key = 3,
//         work_hub_id = 2,
//         status = "In Progress"
//     )
// )

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun Home(name: String, navController: NavController, sharedViewModel: SharedViewModel) {
    val datedialogueState = rememberMaterialDialogState()
    val viewmodel : HomeViewModel = hiltViewModel()
    val workhub = viewmodel.workhub.collectAsState().value
    var name = "technovia"
    var description = "description"
    val homeViewModel: HomeViewModel = hiltViewModel()
    LaunchedEffect (Unit){
        homeViewModel.gettasks(sharedViewModel.user.value?.id!!)
    }
//    val dummyTasks= listOf(<Task>)
    val dummyTasks=homeViewModel.tasks.collectAsState().value
    println(workhub)
    if(workhub!=null){
        sharedViewModel.updateWorkhub(workhub)
        name = workhub.name
        description = workhub.description
    }

    LaunchedEffect(Unit) {
        viewmodel.getworkhub(sharedViewModel.user.value?.id.toString())
    }
    val font = FontFamily(Font(R.font.kaushanscript))
    val joseph = FontFamily(Font(R.font.josefinsansbold))
    val role= sharedViewModel.user.value?.role


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


        // Render buttons based on user role
        when (role) {
            "admin" -> {
                AdminButtons(navController)
            }
            "ProjectLeader" -> {

                ProjectLeaderButtons(navController)
                AssignedTasksList(dummyTasks)
            }
            "employee" -> {
                AssignedTasksList(dummyTasks)
            }
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


@Composable
fun AdminButtons(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        AddTaskButton(navController)
        AddProjectButton(navController)
    }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        AddEmployeesButton(navController)
    }
}

@Composable
fun ProjectLeaderButtons(navController: NavController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp)
    ) {
        AddTaskButton(navController)
    }
}

@Composable
fun AddTaskButton(navController: NavController) {
    Surface(
        modifier = Modifier
            .shadow(20.dp)
            .fillMaxWidth(0.35f)
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(8.dp)
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.hsl(220f,0.8f,0.5f)),

            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color.hsl(265f, 0.55f, 0.50f),
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                navController.navigate(Navscreen.Createtask.route)
            },
        ) {
            Text(text = "Add Task", color = Color.White)
        }
    }
}

@Composable
fun AddProjectButton(navController: NavController) {
    Surface(
        modifier = Modifier
            .shadow(20.dp)
            .fillMaxWidth(0.5f)
            .background(color = Color.Transparent),
        shape = RoundedCornerShape(8.dp),

    ) {
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.hsl(220f,0.8f,0.5f)),

            modifier = Modifier
                .background(
                    color = Color.hsl(265f, 0.55f, 0.50f),
                    shape = RoundedCornerShape(10.dp)
                ),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                navController.navigate(Navscreen.CreateProject.route)
            },
        ) {
            Text(text = "Add Project", color = Color.White)
        }
    }
}

@Composable
fun AddEmployeesButton(navController: NavController) {

        Surface(
            modifier = Modifier

                .padding(16.dp)
                .shadow(20.dp)
                .fillMaxWidth(0.7f)
                .background(color = Color.Transparent),
                shape = RoundedCornerShape(8.dp)
        ) {
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.hsl(220f,0.8f,0.5f)),
                modifier = Modifier
                    .background(
                        color = Color.Cyan
                    )
                    .fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                onClick = {
                    // navController.navigate(Navscreen.AddEmployees.route)
                },
            ) {
                Text(
                    text = "Add Employers",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontSize = 18.sp,
                        color = Color.White
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(10.dp)
                        .fillMaxWidth() // Make Text fill the entire width inside the Surface
                )
            }
        }

}




@Composable
fun AssignedTasksList(tasks: List<Task>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        items(tasks) { task ->
            TaskItem(task = task)
        }
    }
}

@Composable
fun TaskItem(task: Task) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        tonalElevation = 4.dp
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = task.name,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = task.description,
                style = TextStyle(fontSize = 14.sp)
            )
            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Due Date: ${task.deadline}",
//                style = TextStyle(fontSize = 14.sp)
//            )
        }
    }
}
