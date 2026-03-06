package com.example.composelab.section3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelab.MyText
import com.example.composelab.section3.ui.theme.ComposelabTheme

class Test3Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {
    // 버튼을 클릭 했을 때 box에 겹쳐진 composable이 나오는 상태를 조정하겠다.
    // 버튼 클릭시에 화면이 바뀌어야 한다. ==> re-composition이 발생해야 가능하다 ==> 상태가 있어야 한다.
    // visible 상태
    var visibleState by remember {
        mutableStateOf(mutableListOf(true, false))
    }

    Column(modifier = modifier) {
        Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
            // composable 의 visible을 조정해서 화면에 출력 상태를 바꾸고.. 그대 애니메이션 효과
            // this@Column
            // this@MainActivity 이런 형식으로 사용했다.
            // 결과적으로 하고 싶은 것은
            // 현 객체 @AAA 라벨 표현식.
            // 모든 클래스, 함수는 우리가 지정하지 않아도 자동으로 그 이름으로 라벨이 지정된다.
            // this@Column ==> Box 스코프를 실행하는 객체가 아니라 Column 스코프의 객체이다.
            this@Column.AnimatedVisibility(
                visible = visibleState.get(0),
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                MyText(data = "A")
            }
            this@Column.AnimatedVisibility(
                visible = visibleState.get(1),
                enter = fadeIn(),
                exit = fadeOut(),
                modifier = Modifier.align(Alignment.Center)
            ) {
                MyText(data = "B")
            }
        }
        Row {
            Button(modifier = Modifier.weight(1f), onClick = {
                // 상태 값 변경.. 화면 재구성되게
                visibleState = mutableListOf(true, false)
            }) {
                Text("A")
            }
            Button(modifier = Modifier.weight(1f), onClick = {
                // 상태 값 변경.. 화면 재구성되게
                visibleState = mutableListOf(false, true)
            }) {
                Text("B")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ComposelabTheme {
        Greeting3("Android")
    }
}