package com.example.composelab.section4

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.composelab.R
import com.example.composelab.section4.ui.theme.ComposelabTheme

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
    // rememberScrollState 사용 이유 : 화면이 오버되면 자동 스크롤이 안된다. 그래서 화면이 스크롤 되게끔 해야한다.
    val state = rememberScrollState()
    val context = LocalContext.current

    Column(modifier = Modifier.verticalScroll(state).then(modifier)) {
        Text(
            text = stringResource(R.string.app_name).repeat(20),
            color = Color.Red,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(5.dp))

        // 리소스 이미지
        Image(
            painter = painterResource(R.drawable.flower1),
            contentDescription = "" // 이미지 설명
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Bitmap
        val bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.flower1)
        Image(
            bitmap = bitmap.asImageBitmap(),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(5.dp))

        // Icon
        // 구글에서 지원하는 Icon이미지
        Image(imageVector = Icons.Default.Home, contentDescription = "")
        Image(imageVector = Icons.Outlined.Home, contentDescription = "")
        Spacer(modifier = Modifier.height(5.dp))

        // network image coil
        AsyncImage(
            model = "https://picsum.photos/200/300",
            contentDescription = ""
        )
        Spacer(modifier = Modifier.height(5.dp))

        Image(
            painter = painterResource(R.drawable.dog1),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposelabTheme {
        Greeting("Android")
    }
}