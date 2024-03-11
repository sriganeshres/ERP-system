import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material.icons.outlined.Chat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Work
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.work.workhubpro.ui.Navigation.Navscreen
import com.work.workhubpro.ui.screens.Community
import com.work.workhubpro.ui.screens.Home
import com.work.workhubpro.ui.screens.Projects

data class BottomnavItems(
    val title:String,
    val route : String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)
val items = listOf(
    BottomnavItems(
        title="home",
        route = Navscreen.Home.route,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    ),
    BottomnavItems(
        title="projects",
        route = Navscreen.Projects.route,
        selectedIcon = Icons.Filled.Work,
        unselectedIcon = Icons.Outlined.Work,
    ),
    BottomnavItems(
        title="community",
        route = Navscreen.Community.route,
        selectedIcon = Icons.Filled.Chat,
        unselectedIcon = Icons.Outlined.Chat
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bottombar(navController: NavController) {
    val navigation = rememberNavController()
    var selectedItemindex by rememberSaveable {
        mutableIntStateOf(0)
    }
    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navigation.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {it.route == item.title} == true ,

                        onClick = { selectedItemindex = index
                                  navigation.navigate(item.route){
                                      popUpTo(navigation.graph.findStartDestination().id){
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }},
                        icon = {
                            BadgedBox(
                                badge = {

                                }) {
                                Icon(
                                    imageVector = if (index == selectedItemindex) {
                                        item.selectedIcon
                                    } else item.unselectedIcon, contentDescription = item.title
                                )
                            }
                        })
                }
            }
        },

        ) {paddingValues ->
        NavHost(navController = navigation ,startDestination = Navscreen.Home.route,
        modifier = Modifier.padding(paddingValues)) {
            composable(route = Navscreen.Home.route) {
                Home(navController = navigation)
            }
            composable(route = Navscreen.Community.route) {
                Community(navController = navigation)
            }
            composable(route = Navscreen.Projects.route) {
                Projects(navController = navigation)
            }
        }
    }
}
@Preview
@Composable
fun BottombarPreview() {
    val navController = rememberNavController()
    Bottombar(navController = navController)
}