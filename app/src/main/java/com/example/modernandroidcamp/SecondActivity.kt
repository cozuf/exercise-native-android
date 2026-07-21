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

class SecondActivity : ComponentActivity() {
    private val TAG = "SecondActivityLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Gelen mektubu açıyoruz
        val gelenKullaniciAdi = intent.getStringExtra(EXTRA_KULLANICI_ADI)
        val gelenGun = intent.getIntExtra(EXTRA_KAMP_GUNU, 0) // Int değerlerde varsayılan bir değer (0) ister

        // Logcat'e yazdırıp kontrol ediyoruz
        Log.d(TAG, "SecondActivity'ye başarıyla geçiş yapıldı!")
        Log.d(TAG, "Gelen Kullanıcı Adı: $gelenKullaniciAdi")
        Log.d(TAG, "Gelen Kamp Günü: $gelenGun")
    }

    // --- TYPESCRIPT ENUM GİBİ ÇALIŞAN SABİTLERİMİZ ---
    companion object {
        const val EXTRA_KULLANICI_ADI = "com.example.myapplication.EXTRA_KULLANICI_ADI"
        const val EXTRA_KAMP_GUNU = "com.example.myapplication.EXTRA_KAMP_GUNU"
    }
}
