package com.work.workhubpro.ui.screens.loginPage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.work.workhubpro.R
import com.work.workhubpro.ui.composables.CheckBoxComposable
import com.work.workhubpro.ui.composables.HeadingTextComposable
import com.work.workhubpro.ui.composables.MyTextField
import com.work.workhubpro.ui.composables.NormalTextComposable
import com.work.workhubpro.ui.composables.PasswordTextField
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.screens.signup.LoginPageViewModel

@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    // List of roles for the dropdown menu
    val roles = listOf("Role 1", "Role 2", "Role 3", "Role 4")

    // State to manage the dropdown menu visibility
    var expanded by remember { mutableStateOf(false) }
    // State to store the selected role
    var selectedRole by remember { mutableStateOf("") }
    val loginViewModel: LoginPageViewModel = hiltViewModel<LoginPageViewModel>()

    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NormalTextComposable(value = stringResource(id = R.string.hey_there))
            HeadingTextComposable(value = stringResource(id = R.string.login))
            Spacer(modifier = Modifier.height(20.dp))
            MyTextField(
                labelValue = stringResource(id = R.string.username),
                painterResource(id = R.drawable.outline_edit_black_24dp),
                textValue = username,
                onValueChange = { username = it }

            )

            MyTextField(labelValue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.outline_mail_outline_black_20),
                textValue = email,
                onValueChange = { email = it })
            PasswordTextField(labelValue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.outline_password_black_20),
                textValue = password,
                onValueChange = { password = it })
            Spacer(modifier = Modifier.height(16.dp))
            CheckBoxComposable(value = stringResource(id = R.string.terms_and_conditions))
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                loginViewModel.loginuser(username, email, password)
                navController.navigate(Navscreen.Bottom.route + "/${username}")
            }
            ) {
                Text(text = "Login")
            }

            // Dropdown menu for selecting role
//            Box(modifier = Modifier.fillMaxWidth()) {
//                Button(
//                    onClick = { expanded = true },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Text(text = "Select Role: ${if (selectedRole.isEmpty()) "Select" else selectedRole}")
//                }
//
//                DropdownMenu(
//                    expanded = expanded,
//                    onDismissRequest = { expanded = false },
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    roles.forEach { role ->
//                       DropdownMenuItem(onClick = {selectedRole=role,expanded = false}) {
//                           Text(text = role)
//                       }
//                    }
//                }
//            }

        }
    }
}
