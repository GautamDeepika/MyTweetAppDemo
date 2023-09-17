package com.example.mytweetappdemo.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mytweetappdemo.ui.theme.MyTweetAppDemoTheme
import com.example.mytweetappdemo.ui.theme.PurpleGrey80
import com.example.mytweetappdemo.views.screens.CategoryScreen
import com.example.mytweetappdemo.views.screens.DetailScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    @Inject
//    lateinit var iApiService: IApiService

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        GlobalScope.launch {
//            var res = iApiService.getCategories()
//            Log.d("HELLO", res.body()!!.distinct().toString())
//            //[motivation, system design, android, real life facts]
//        }
        setContent {
            MyTweetAppDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(90,98,113)
                ) {
//                    Greeting("Android")
//                    CategoryScreen()
//                    DetailScreen()
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(text = "Tweet App")
                                },
                                modifier = Modifier
                                    .statusBarsPadding()
                                    .background(Color(90,98,113)),
                            )
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            App()
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "category"
    ) {
        composable(route = "category") {
            CategoryScreen(onClick = {
                navController
                    .navigate("detail/${it}")
            })
        }
        composable(route = "detail/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen()
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyTweetAppDemoTheme {
//        Greeting("Android")
//    }
//}

