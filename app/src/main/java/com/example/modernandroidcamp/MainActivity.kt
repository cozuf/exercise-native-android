package com.example.modernandroidcamp

import android.content.Intent
import android.net.Uri
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



    // 1. EKRAN İLK KEZ OLUŞTURULURKEN (Dün yazdığımız yer)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "LIFECYCLE: onCreate tetiklendi! Ekran belleğe yüklendi.")

        if (savedInstanceState == null) {
            // 1. Profil nesnemizi oluşturuyoruz (githubUrl bilinçli olarak null verilebilir ya da doldurulabilir)
            val profilim = KullaniciProfili(
                id = 1,
                isim = "Yusuf Coşkun",
                unvan = "Mobile Software Developer",
                githubUrl = "https://github.com/yusufcoskun",
                tecrubeYili = 6
            )

            // 2. SecondActivity'nin 'newIntent' helper'ı sayesinde Intent detaylarıyla uğraşmadan ekranı başlatıyoruz!
            val intent = SecondActivity.newIntent(this, profilim)
            startActivity(intent)
        }

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