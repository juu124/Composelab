package com.example.composelab.section4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelab.section4.ui.theme.ComposelabTheme

class Test2Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposelabTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting2(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    val state = rememberScrollState()
    val context = LocalContext.current

    Column(modifier = Modifier
        .verticalScroll(state)
        .then(modifier)) {
        // 선언형 ui 프로그램이다.
        // 정보만 준다. 화면 재구성하지 않는한 변경사항 화면에 적용되지 않는다.
        // 유저 입력에 의해 화면이 변경되는 컴포저블(Checkbox, Slider, Switch, Textfield...)
        // 상태값으로 유저 입력 값을 표현해야 하고 유저 입력값에 의해 상태 변경되게 해야한다.
        var checkState by remember {
            mutableStateOf(false)
        }
        Checkbox(
            checked = checkState,
            onCheckedChange = {
                // 체크 이벤트 처리
                checkState = it
            }
        )

        val arrayData = arrayOf("data1", "data2", "data3")
        val (selected, onChangeSelected) = remember { mutableStateOf(arrayData[0]) }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .selectableGroup() // 구성요소 단일 선택
                .padding(10.dp)
        ) {
            arrayData.forEach {
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                    modifier = Modifier
                        .weight(1f)
                        .selectable(
                            selected = (it == selected),    // 선택 상태
                            onClick = { onChangeSelected(it)}
                        )
                ) {
                    if (selected == it)
                        Text(it, color = Color.Red)
                    else
                        Text(it, color = Color.Black)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposelabTheme {
        Greeting2("Android")
    }
}