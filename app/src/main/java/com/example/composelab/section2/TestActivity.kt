package com.example.composelab.section2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.composelab.section2.ui.theme.ComposelabTheme
import kotlin.random.Random


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
    Log.d("jay", "recomposition............")

    // 상태 변수가 아니다. 값 변경은 가능하지만, 화면 재구성과는 관련없다.
    // 이 변수값이 변경되었다고해도 composable이 다시 호출되지는 않는다.
    var data1 = 0       // 일반 변수

    // 상태 선언.. 이 값이 변경되면 composable 함수가 다시 호출되어
    // 변경된 정보대로 re-composition(재구성) 된다.
    // 재구성시에 다시 이 부분이 실행되어 초기값을 유지한다. 화면에 값 변경 안된다.
    val (data2, setData2) = mutableStateOf(0)

    // 상태 선언. 재구성시에 기존 상태값 유지하라
    var data3 by remember {
        mutableIntStateOf(0)
    }

    // 상태 선언, 값 유지, 액티비티 상태 변화시에 값 유지하게 하기
    var data4 by rememberSaveable {
        mutableStateOf(0)
    }

    fun changeData() {
        Log.d("jay", "changeData............")
        data1 = Random.nextInt(0, 100)
        setData2(Random.nextInt(0, 100))
        data3 = Random.nextInt(0, 100)
        data4 = Random.nextInt(0, 100)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(text = "data1 :$data1")
        Text(text = "data2 :$data2")
        Text(text = "data3 :$data3")
        Text(text = "data4 :$data4")
        Button(onClick = { changeData() }) {
            Text(text = "Change Data")
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