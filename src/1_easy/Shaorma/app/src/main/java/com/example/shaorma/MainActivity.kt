package com.example.shaorma

import androidx.compose.ui.Alignment
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shaorma.ui.theme.ShaormaTheme
import java.security.MessageDigest
import androidx.compose.foundation.Image
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShaormaTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val eureka = "929e5e7240c11085f641cf729a93d329"
    val resId = LocalContext.current.resources.getIdentifier("ab6fda3a34f425c04e43baf3c43a62f", "drawable", LocalContext.current.packageName)

    if (resId != 0) {
        Image(
            painter = painterResource(id = resId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter text") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val hmmm = boom(text)
            message = if (hmmm == eureka) {
                "The mystery is in the shaorma"
            } else {
                "You suck"
            }
        }) {
            Text("Check Text")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = message)
    }
}

fun boom(text: String): String {
    val md = MessageDigest.getInstance("MD5")
    val digest = md.digest(text.toByteArray())
    return digest.joinToString("") { "%02x".format(it) }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    ShaormaTheme {
        MainScreen()
    }
}
