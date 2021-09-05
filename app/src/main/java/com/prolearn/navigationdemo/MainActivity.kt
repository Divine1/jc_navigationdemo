package com.prolearn.navigationdemo

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.prolearn.navigationdemo.model.UserModel
import com.prolearn.navigationdemo.ui.theme.NavigationDemoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    UsersApplication()
                }
            }
        }
    }
}



@Composable
fun UsersApplication(){
    val navController = rememberNavController();
    NavHost(navController = navController, startDestination = "users_list" ){
        composable("users_list"){
            UserListScreen(navController)
        }
        composable(
            route = "user_profile/{userId}",
            arguments = listOf(navArgument("userId"){
                type = NavType.IntType
            })
        ){navBackStackEntry ->
            UserProfileScreen(navBackStackEntry.arguments!!.getInt("userId"),navController)
        }
    }
}

@Composable
fun UserListScreen(navController : NavHostController){
    val appViewModel : AppViewModel = viewModel();
    
    Column() {
        for (item in appViewModel.getUserList()){

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Name ${item.name}")
                moveToNextpage(){
                    navController.navigate("user_profile/${item.id}")
                }
            }

        }
    }


}
@Composable
fun moveToNextpage(callback : ()->Unit ){
    Button(onClick = {
        callback();
    }) {
        Text(text = "view")
    }
}


@Composable
fun UserProfileScreen(userId : Int,navController : NavHostController){
    val appViewModel : AppViewModel = viewModel();
    Log.d("navigationdemo", "userId $userId")

    val userModel : UserModel? = appViewModel.getUserById(userId);
    Log.d("navigationdemo", "userModel $userModel")

    Column() {
        Text(text = "user profile screen");
        Text(text = "name ${userModel?.name}")
        Text(text = "city ${userModel?.city}")
        Text(text = "description ${userModel?.description}")
        Text(
            modifier=Modifier.clickable(){
                //navController.navigateUp();
                navController.navigate("users_list")
            },
            text = "BACK")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NavigationDemoTheme {

    }
}