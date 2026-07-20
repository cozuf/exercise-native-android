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

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        // 1. İLK LOGUMUZ: Uygulamanın başarıyla başladığını işletim sistemine ve kendimize raporluyoruz
//        Log.d(TAG, "Uygulama başarıyla başlatıldı ve onCreate tetiklendi!")
//        Log.d(TAG, "Manifest içindeki INTERNET izni şu an aktif durumda.")
//
//        enableEdgeToEdge()
//        setContent {
//            ModernAndroidCampTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
//            }
//        }
//    }

    // 1. EKRAN İLK KEZ OLUŞTURULURKEN (Dün yazdığımız yer)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "LIFECYCLE: onCreate tetiklendi! Ekran belleğe yüklendi.")

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

    // 2. EKRAN KULLANICIYA GÖRÜNÜR HALE GELİRKEN
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "LIFECYCLE: onStart tetiklendi! Ekran şu an görünür ama henüz etkileşime açık değil.")
    }

    // 3. EKRAN TAMAMEN ÖN PLANDA VE ETKİLEŞİME AÇIKKEN
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "LIFECYCLE: onResume tetiklendi! Uygulama şu an tamamen aktif ve odaklanmış durumda.")
        Log.d(TAG, "STRATEJİ: İleride Socket bağlantısını canlı tutma/yenileme işini tam burada tetikleyeceğiz!")
    }
    // 4. UYGULAMA ODAĞINI KAYBEDERKEN (Örn: Üstten bildirim paneli indiğinde veya başka bir ekran açılırken)
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "LIFECYCLE: onPause tetiklendi! Ekran odağını kaybediyor.")
    }

    // 5. UYGULAMA ARKA PLANA ATILDIĞINDA (Home tuşuna basıldığında veya başka uygulamaya geçildiğinde)
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "LIFECYCLE: onStop tetiklendi! Ekran artık tamamen görünmez durumda.")
        Log.d(TAG, "STRATEJİ: Pil ve internet tasarrufu için SOCKET bağlantısını tam burada KESECEĞİZ!")
    }

    // 6. UYGULAMA TAMAMEN KAPATILDIĞINDA VEYA EKRAN DÖNDÜRÜLDÜĞÜNDE
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "LIFECYCLE: onDestroy tetiklendi! Aktivite bellekten tamamen siliniyor.")
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