package com.example.ivdrip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ivdrip.ui.theme.IVDripTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment
import android.util.Log

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec
import android.util.Base64
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            IVDripTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val vwhsebacbqwkxn = "I12IWF7IB77/A7ugrl8Mf+pGQ5TPUCTkQJkaKir2+415ouC+slTa3MfqpOfNVkERHVqxeZvX94KwcJHKL26f5uismx6YaKQxrcWMhWkYufU="
                    val JNFEAJBURWBECHBQ = "Why_so_salty_ayo"
//                    val initVector = "_coffee__coffee_"
                    val nvwfecbvuhrewqchbrvew = "This_ain_t_no_flag_bro"
                    val evrbweuvbeqr = bvrwebcwbnec(vwhsebacbqwkxn, JNFEAJBURWBECHBQ, nvwfecbvuhrewqchbrvew)
                    Log.d("\uD83E\uDD14", evrbweuvbeqr)

                    Greeting(
                        name = evrbweuvbeqr,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = R.drawable.background_image),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "",
            textAlign = TextAlign.Center,
            color = Color.Red
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IVDripTheme {
        Greeting("Guard the treasure with ancient secrets. Combine the sun and the moon at the break of dawn. Remember, the initial pulse dances with the seasoned sea.")
    }
}

fun bvrwebcwbnec(dcasnjvrwe: String, jersdnvjrkwfec: String, IVfsadhsjahhfewka: String): String {
    try {
        val nvjfnsdj = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        nvjfnsdj.init(Cipher.DECRYPT_MODE,
            SecretKeySpec(jersdnvjrkwfec.toByteArray(Charsets.UTF_8), "AES"),
            IvParameterSpec(IVfsadhsjahhfewka.toByteArray(Charsets.UTF_8)))

        val original = nvjfnsdj.doFinal(Base64.decode(dcasnjvrwe, Base64.DEFAULT))

        return String(original)
    } catch (ex: Exception) {
        ex.printStackTrace()
    }

    return ""
}
