package com.work.workhubpro.ui.screens.projects

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.work.workhubpro.R
import com.work.workhubpro.SharedViewModel
import com.work.workhubpro.ui.composables.ProjectHeading
import com.work.workhubpro.ui.navigation.Navscreen
import dagger.hilt.android.lifecycle.HiltViewModel

data class ProjectItem(
    val projectImage: Any, val projectName: String, val projectDescription: String
)

val dummyProjectList = List(10) { index ->
    ProjectItem(
        projectImage = Any(),  // Placeholder for project image
        projectName = "Project_${index + 1}",  // Assigning unique project names
        projectDescription = "Description for Project ${index + 1}"  // Assigning descriptions
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ProjectListScreens(navController: NavController, sharedViewModel: SharedViewModel) {
    val projectsListViewModel: ProjectsListViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        println(sharedViewModel.user.value)
        projectsListViewModel.getallprojects(sharedViewModel.user.value?.id.toString())
    }

    val projects = projectsListViewModel.projects.collectAsState().value
    println("hi_hello")
    println(projects)
    val colors = listOf(Color(0xFF007bff), Color(0xFF673ab7)) // Deep blue and purple gradient
    val rippleColor = Color.White.copy(alpha = 0.2f)
    val gradient = Brush.linearGradient(
        colors = colors + rippleColor, start = Offset.Zero, end = Offset.Infinite
    )
    Column(
        modifier = Modifier.background(
            color = Color.hsl(241F, 0.64F, 0.18F)
        ), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProjectHeading(value = stringResource(id = R.string.Projects_list))
        LazyColumn(
            modifier = Modifier.background(
                shape = MaterialTheme.shapes.small, color = Color.hsl(241F, 0.64F, 0.18F)
            ),
            contentPadding = PaddingValues(vertical = 3.dp)
        ) {
            items(projects.distinct()) { project ->
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .animateContentSize()
                    .background(
                        gradient
                    )
                    .padding(9.dp)
                    .clickable(interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(bounded = false, color = rippleColor),
                        onClick = {
                            navController.navigate(Navscreen.ProjectDetails.route + "/${project.ID}")
                        }), verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.wall),
                        contentDescription = stringResource(id = R.string.Profile_photo),
                        modifier = Modifier
                            .width(80.dp)
                            .height(80.dp),
                        colorFilter = null
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = project.name, // Using projectName instead of name
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Cyan
                        )
                        Text(
                            text = project.description, // Using projectDescription instead of description
                            style = MaterialTheme.typography.bodySmall,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.hsl(176F, 0.36F, 0.83F)
                        )
                    }
                }
            }
        }
    }
}

