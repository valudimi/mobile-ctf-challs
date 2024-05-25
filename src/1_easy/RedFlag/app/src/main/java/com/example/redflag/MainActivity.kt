package com.example.redflag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.redflag.ui.theme.RedFlagTheme
import kotlin.math.absoluteValue

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RedFlagTheme {
                ProfileApp()
            }
        }
    }
}

@Composable
fun ProfileApp() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        ProfileScreen(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    val profiles = remember {
        mutableStateListOf(
            Profile("Alice", 29, "Reading, Gardening", "f853b10af73a04a4c8b35b2ade1f40b9"),
            Profile("Bob", 24, "Hiking, Chess", "6209314d115dedaed8ff201a24df027a"),
            Profile("Cara", 31, "Cooking, Photography", "4ff89342bb46cea91a288c3bed86e432"),
            Profile("Andrea", 31, "Cooking, Photography", "7f60ced0d4a58668c18db95ab2f48685449c9766edda69b6dddf7759ba58965f"),
            Profile("Smara", 31, "Cooking, Photography", "0fec5f56abe6b4266223c6547ec01b33eb6ff01b"),
            Profile("Jinny", 31, "Cooking, Photography", "4ff8934w3w46cea91a288crafd86e1b2"),
            Profile("Cecilia", 31, "Cooking, Photography", "3f3f82f0deccd0e145b413462eccb4f2")
        )
    }

    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (profiles.isNotEmpty()) {
            SwipeableProfileCard(profile = profiles.first(), onDismiss = { profiles.removeAt(0) })
        } else {
            Text("No more fish in the sea for you.")
        }
    }
}

@Composable
fun SwipeableProfileCard(profile: Profile, onDismiss: () -> Unit) {
    var offsetX by remember { mutableStateOf(0f) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .offset(x = offsetX.dp)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    offsetX += dragAmount.x
                    if (offsetX.absoluteValue > 300) { // swipe threshold
                        offsetX = 0f // reset position or animate out
                        onDismiss()
                    }
                }
            }
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Name: ${profile.name}", style = MaterialTheme.typography.titleLarge)
            Text("Age: ${profile.age}", style = MaterialTheme.typography.bodyMedium)
            Text("Hobbies: ${profile.hobbies}", style = MaterialTheme.typography.bodySmall)
            Text("About: ${profile.about}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

data class Profile(val name: String, val age: Int, val hobbies: String, val about: String)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RedFlagTheme {
        ProfileApp()
    }
}
