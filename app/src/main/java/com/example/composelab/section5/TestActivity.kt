package com.example.composelab.section5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composelab.section5.ui.theme.ComposelabTheme

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(name: String, modifier: Modifier = Modifier) {
    // 화면 전환 명령 api 제공
    val navController = rememberNavController()

    // navController를 등록해서 navController에 의해 전환된 화면을 스택 정보로 관리하는 NavHost
    // startDestination : 첫 화면 지정
    NavHost(navController = navController, startDestination = "home") {
        // 화면 composable을 식별자 문자열로 등록
        composable("home") { HomeScreen(navController) }
        composable("one") { OneScreen(navController) }
        composable("oneSub") { OneSubScreen(navController) }
        composable("two") { TwoScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am Home Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Button(onClick = { navController.navigate("one") }) {
            Text("Go One")
        }
        Button(onClick = { navController.navigate("two") }) {
            Text("Go Two")
        }
    }
}

@Composable
fun OneScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am One Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Button(onClick = {
            navController.navigate("oneSub") {
                // oneSub으로 화면전환할때 작업을 명시할 수 있다.
                // home 이전까지의 stack 정보를 제거한 후에 이동
                popUpTo("home")
            }
        }) {
            Text("Go OneSub")
        }
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
fun OneSubScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am OneSub Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Composable
fun TwoScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am Two Screen", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Button(onClick = { navController.popBackStack() }) {
            Text("Go Back")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ComposelabTheme {
        MainScreen("Android")
    }
}