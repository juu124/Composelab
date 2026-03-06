package com.example.composelab.section3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.composelab.section3.ui.theme.ComposelabTheme

class Test4Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting4(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

// ConstraintSet
// 컴포저블에서 ConstraintLayout을 사용하면서 제약조건을 설정할 수 있다.
// 제약조건이 길거나, 동일 제약이 여러곳에 반복될 때 사용한다.
private fun myConstraint(): ConstraintSet {
    // 아래에 있는 제약 조건을 어디에선가 사용하게된다.
    return ConstraintSet {
        // 식별자 변수
        // 이 코드 위치에서만 식별자를 부여할 수 있다.
        // 만들 때 지정한 문자열로 컴포저블은 이용한다.
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button) {
            top.linkTo(parent.top, margin = 10.dp)
            start.linkTo(parent.start, margin = 10.dp)
        }
        constrain(text) {
            top.linkTo(button.bottom, margin = 10.dp)
            start.linkTo(parent.end, margin = 10.dp)
        }
    }
}

@Composable
fun Greeting4(name: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val constraintSet= myConstraint()

        ConstraintLayout(
            constraintSet = constraintSet,
            modifier = Modifier.background(Color.Green)
                .fillMaxWidth()
                .height(200.dp)
        ) {
            // Modifier.layoutId("button") - ConstraintSet에 선언된 "button"을 적용하라
            Button(onClick = {}, modifier = Modifier.layoutId("button")) {
                Text("Button")
            }
            Text("Hello", modifier = Modifier.layoutId("text"))
        }

        ConstraintLayout(modifier = Modifier
            .background(Color.Yellow)
            .fillMaxWidth()
            .height(200.dp)
        ) {
            val guideLine1 = createGuidelineFromStart(0.5f)   // 50%
            val guideLine2 = createGuidelineFromTop(100.dp)

            val (button1, text1) = createRefs()
            // constrainAs(button1) - Button 은 식별자 "button1"로 식별된다.
            Button(onClick = {}, modifier = Modifier.constrainAs(button1) {
                top.linkTo(guideLine2, margin = 0.dp)
                start.linkTo(guideLine1, margin = 0.dp)
            }) {
                Text("button")
            }

            Text("hello", modifier = Modifier.constrainAs(text1) {
               top.linkTo(button1.bottom, margin = 0.dp)
                start.linkTo(guideLine1, margin = 0.dp)
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    ComposelabTheme {
        Greeting4("Android")
    }
}