package com.example.composelab.section4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelab.section4.ui.theme.ComposelabTheme

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
    Column(modifier = modifier) {
        // 유저 입력 데이터 상태로 만들어야 입력한 내용이 화면에 출력된다.
        var data by remember {
            mutableStateOf("")
        }
        var isVisiblePassword by remember { mutableStateOf(false) }

        // trailing icon 을 만드는 composable
        val trailingIcon = @Composable {
            val icon = if (isVisiblePassword) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            IconButton(onClick = {
                isVisiblePassword = !isVisiblePassword
            }) {
                Icon(imageVector = icon, contentDescription = "")
            }
        }
        OutlinedTextField(
            value = data,
            onValueChange = { data = it },
            label = { Text("Password") },
            shape = RoundedCornerShape(20.dp),
            // TextField에 보이는 문자열 변형이 필요하다면 사용해야 한다.
            visualTransformation = if (isVisiblePassword) {
                VisualTransformation.None   // 아무 처리도 하지마라. 유저 입력 문자열 그대로 찍어라
            } else {
                PasswordVisualTransformation()
            },
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(Icons.Outlined.Lock, "")
            },
            trailingIcon = trailingIcon
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    ComposelabTheme {
        Greeting3("Android")
    }
}