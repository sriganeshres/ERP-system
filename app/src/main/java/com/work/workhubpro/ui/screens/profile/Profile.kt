package com.work.workhubpro.ui.screens.profile
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.work.workhubpro.R
import com.work.workhubpro.SharedViewModel
import com.work.workhubpro.models.User
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.theme.Lightblue2
import com.work.workhubpro.ui.theme.mediumblue2
import com.work.workhubpro.ui.theme.profile
import com.work.workhubpro.ui.theme.team

@Composable
fun Info(user:User){

    val heading = FontFamily(Font(R.font.dmserif))
    val infont = FontFamily(Font(R.font.deliusswash))
    Column(
        modifier = Modifier.background(mediumblue2)) {
            Text(
                text = "Email :",
                style = TextStyle(
                    fontFamily = heading,
                    fontSize = 33.sp,
                    color = Color.White
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            )
        Text(
            text = user.email,
            style = TextStyle(
                fontFamily = infont,
                fontSize = 30.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            modifier = Modifier.padding(8.dp),
            color = Color.White,
            thickness = 1.dp
        )
        Text(
            text = "Attendence:",
            style = TextStyle(
                fontFamily = heading,
                fontSize = 33.sp,
                color = Color.White
            ),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Text(
            text = "13/365",
            style = TextStyle(
                fontFamily = infont,
                fontSize = 30.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            modifier = Modifier.padding(8.dp),
            color = Color.White,
            thickness = 1.dp
        )
        Text(
            text = "Role:",
            style = TextStyle(
                fontFamily = heading,
                fontSize = 33.sp,
                color = Color.White
            ),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Text(
            text = user.role!!,
            style = TextStyle(
                fontFamily = infont,
                fontSize = 30.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(8.dp)
        )
        Divider(
            modifier = Modifier.padding(8.dp),
            color = Color.White,
            thickness = 1.dp
        )
        Text(
            text = "Leaves:",
            style = TextStyle(
                fontFamily = heading,
                fontSize = 33.sp,
                color = Color.White
            ),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        Text(
            text = "5",
            style = TextStyle(
                fontFamily = infont,
                fontSize = 30.sp,
                color = Color.White
            ),
            modifier = Modifier.padding(8.dp)
        )
    }
}
@Composable
fun Profile(navController: NavController,sharedViewModel: SharedViewModel) {
    val user = sharedViewModel.user.collectAsState().value
    val profileViewModel:ProfileViewModel = hiltViewModel()
    val tokenmanager = profileViewModel.getTokenManager()
    val heading = FontFamily(Font(R.font.dmserif))
    val dancing = FontFamily(Font(R.font.dancingscript))
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

        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .size(100.dp)
                .clip(CircleShape)
        )

            Text(
                text = user!!.username,
                style = TextStyle(
                    fontFamily = heading,
                    fontSize = 30.sp,
                            color = Color.Black,
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        Surface(
            modifier = Modifier
                .shadow(20.dp)
                .fillMaxWidth(0.5f)
                .background(color = Color.Transparent)
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(8.dp),
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
             tokenmanager.saveToken(null)
                    sharedViewModel.updateUser(null)
                    navController.navigate(Navscreen.Landing.route)
                },

            ) {
                Text(text = "Logout",color=Color.White)
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .weight(1f),
                color = profile

            ) {
                Text(
                    text = "Profile",
                    style = TextStyle(
                        fontFamily = dancing,
                        fontSize = 38.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Surface(
                modifier = Modifier
                    .weight(1f),
                color = team

            ) {
                Text(
                    text = "Team",
                    style = TextStyle(
                        fontFamily = dancing,
                        fontSize = 38.sp
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Info(user)
    }
}





