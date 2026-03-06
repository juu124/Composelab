package com.example.composelab.section3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelab.MyText
import com.example.composelab.section3.ui.theme.ComposelabTheme

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
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth()
                .height(300.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            MyText(data = "A")
            MyText(data = "B")
            MyText(data = "C")
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier
                .background(Color.Yellow)
                .fillMaxWidth(),
           horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            MyText(data = "A")
            MyText(data = "B")
            MyText(data = "C")
        }
        Spacer(modifier = Modifier.height(3.dp))
        Row(
            modifier = Modifier
                .background(Color.Yellow)
        ) {
            MyText(data = "A", modifier = Modifier.weight(1f))
            MyText(data = "B", modifier = Modifier.weight(2f))
            MyText(data = "C", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(3.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    ComposelabTheme {
        Greeting2("Android")
    }
}