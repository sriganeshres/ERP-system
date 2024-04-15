package com.work.workhubpro.ui.screens.community

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.work.workhubpro.SharedViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.work.workhubpro.models.User
import com.work.workhubpro.ui.navigation.Navscreen
import com.work.workhubpro.ui.screens.chat.Chat

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "SuspiciousIndentation")
@Composable
fun Community(navController: NavController,sharedViewModel: SharedViewModel){
    val workhub = sharedViewModel.workhub.collectAsState().value
    val dummy = User("ganesh","ejdjowecsecf", "jiejfjef")
    val users:List<User> = listOf<User>(dummy)
    println("hi")
    println(users)


    val userNames = users.map { it.username } ?: emptyList()
    println(userNames)


    Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {Text(text = "User List",
                                onTextLayout = {}) },
                        )
                    }
                ) {
                    UserList(userNames = userNames, navController)
                  }
            }

    @Composable
    fun UserList(userNames: List<String>,navController: NavController) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(userNames) { userName ->
                UserListItem(userName = userName,navController)
            }
        }
    }

    @Composable
    fun UserListItem(userName: String,navController: NavController) {
        Card(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .clickable { navController.navigate("${Navscreen.Chat.route}/$userName") }

        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(text = userName,
                    onTextLayout = {})
            }
        }
    }

