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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        // navigate로 화면 이동하면서 데이터 전달. URL 처럼. 간단한 데이터를 넘기기에 좋다.
        // 개발자 객체를 넘기려면 viewModel로 개발자 데이터를 넘기면 된다.
        // composable 이름에 전달하고자 하는 데이터를 선언한다.
        composable(
            // OneScreen에 이동할때 해당 데이터를 곡 전달해줘야한다.
            "one/{userId}/{no}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                },
                navArgument("no") {
                    type = NavType.IntType
                }
            )
        ) { navBackStackEntry ->
            // OneScreen에 이동할때 해당 데이터를 곡 전달해줘야한다.
            // 그러니 아래 형태 처럼 만들어준다.
            OneScreen(navController,
                navBackStackEntry.arguments?.getString("userId"),
                navBackStackEntry.arguments?.getInt("no")
            )
        }
        composable("oneSub") { OneSubScreen(navController) }
        composable("two") { TwoScreen(navController) }
    }
}

@Composable
fun HomeScreen(navController: NavController) {

    // pop 으로 되돌아 올때 결과 데이터 획득
    val msg = navController.currentBackStackEntry?.savedStateHandle?.get<String>("msg")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am Home Screen : $msg", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Button(onClick = { navController.navigate("one/lee/20") }) {
            Text("Go One")
        }
        Button(onClick = { navController.navigate("two") }) {
            Text("Go Two")
        }
    }
}

@Composable
fun OneScreen(navController: NavController, userId: String?, no: Int?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("I am One Screen - $userId - $no", fontSize = 30.sp, fontWeight = FontWeight.Bold)
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
        Button(onClick = {
            // 결과 데이터 포함
            // two 화면에서 발생한 데이터를 이전 화면으로 가지고 가겠다.
            navController.previousBackStackEntry?.savedStateHandle?.set("msg", "jay")
            navController.popBackStack()
        }) {
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