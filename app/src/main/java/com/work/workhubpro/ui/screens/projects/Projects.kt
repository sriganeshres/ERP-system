package com.work.workhubpro.ui.screens.projects

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.work.workhubpro.SharedViewModel
import com.work.workhubpro.models.User

@Composable
fun Projects(navController: NavController,sharedViewModel:SharedViewModel) {
   val User = listOf<User>()
   LazyVerticalGrid(columns = GridCells.Fixed(2),
       contentPadding = PaddingValues(8.dp),
       verticalArrangement = Arrangement.SpaceAround
   ) {

items(User.distinct()){

}
   }
}