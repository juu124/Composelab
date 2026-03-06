package com.example.composelab.section3

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composelab.section3.ui.theme.ComposelabTheme

class Test1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    // composable을 세로방향으로 순서대로 나열
    Column(modifier = modifier) {
        val myModifier = Modifier
            .border(width = 10.dp, color = Color.Red)   // 어디 화면에서 사용할지는 모르겟지만, 테두리를 그리겠다.
            .padding(30.dp)
            .background(Color.Yellow)

        Text(
            text = "Hello World",
            fontSize = 30.sp,
            // then을 이용해 Modifier 연결 가능하다.
            // 동일한 설정이 있다면 then으로 나중에 지정한 것이 적용된다. 덮어쓰기 개념
            modifier = modifier.then(myModifier).then(Modifier.background(Color.Red))
        )

        // 여백을 차지하는 composable
        Spacer(modifier = Modifier.height(10.dp))

        // Context.. 객체
        // 현재 액티비티의 정보를 지칭한다.
        val context = LocalContext.current

        Card(
            modifier = Modifier.background(Color.Blue)
                .size(200.dp, 100.dp)
                .clickable {
                    // 카드라는 영역을 클릭했을 때 이벤트
                    Toast.makeText(context, "click...", Toast.LENGTH_SHORT).show()
                }) {
            Text("Hello")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposelabTheme {
        Greeting("Android")
    }
}