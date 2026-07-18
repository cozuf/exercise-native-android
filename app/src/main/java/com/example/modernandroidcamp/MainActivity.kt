package com.example.modernandroidcamp

import android.os.Bundle
import android.util.Log
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
import com.example.modernandroidcamp.ui.theme.ModernAndroidCampTheme

class MainActivity : ComponentActivity() {

    // Logları filtrelemek için kullanacağımız benzersiz bir etiket (TAG)
    private val TAG = "AndroidKampLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. İLK LOGUMUZ: Uygulamanın başarıyla başladığını işletim sistemine ve kendimize raporluyoruz
        Log.d(TAG, "Uygulama başarıyla başlatıldı ve onCreate tetiklendi!")
        Log.d(TAG, "Manifest içindeki INTERNET izni şu an aktif durumda.")

        enableEdgeToEdge()
        setContent {
            ModernAndroidCampTheme {
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
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ModernAndroidCampTheme {
        Greeting("Android")
    }
}