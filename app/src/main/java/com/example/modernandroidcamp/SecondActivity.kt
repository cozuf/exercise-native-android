package com.example.modernandroidcamp

import android.content.Context
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

class SecondActivity : ComponentActivity() {
    private val TAG = "SecondActivityLog"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. Gelen Intent verilerini tip güvenli alıyoruz
        val isim = intent.getStringExtra(EXTRA_ISIM) ?: "Bilinmeyen Kullanıcı"
        val unvan = intent.getStringExtra(EXTRA_UNVAN) ?: "Unvan Belirtilmedi"
        val tecrubeYili = intent.getIntExtra(EXTRA_TECRUBE, 0)

        // 2. Null Safety & Elvis Operator (?:)
        // Eğer Github URL'i null geldiyse güvenli bir varsayılan adrese çekiyoruz
        val githubUrl = intent.getStringExtra(EXTRA_GITHUB_URL) ?: "https://github.com"

        Log.d(TAG, "--- PROFİL KARTI ALINDI ---")
        Log.d(TAG, "İsim: $isim | Unvan: $unvan | Tecrübe: $tecrubeYili Yıl")
        Log.d(TAG, "GitHub: $githubUrl")

        // 3. Implicit Intent Eylemleri
        // A) Tarayıcıda GitHub profilini aç
        //githubProfiliniAc(githubUrl)

        // B) Profil özetini dışarıya paylaş
        val kartOzeti = "Yazılımcı Kartı: $isim - $unvan ($tecrubeYili Yıl Tecrübe) | Profil: $githubUrl"
        //kartOzetiPaylas(kartOzeti)
    }

    private fun githubProfiliniAc(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun kartOzetiPaylas(mesaj: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mesaj)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, "Profil Kartını Paylaş:")
        startActivity(shareIntent)
    }

    companion object {
        // Intent Key'leri
        private const val EXTRA_ISIM = "EXTRA_ISIM"
        private const val EXTRA_UNVAN = "EXTRA_UNVAN"
        private const val EXTRA_GITHUB_URL = "EXTRA_GITHUB_URL"
        private const val EXTRA_TECRUBE = "EXTRA_TECRUBE"

        /**
         * SENIOR PRATİK: Static Factory Method (Starter Pattern)
         * MainActivity'nin Intent parametrelerini ezberlemesine gerek kalmaz.
         * SecondActivity kendi başlatılma şartlarını bu fonksiyonla dikte eder.
         */
        fun newIntent(context: Context, profil: KullaniciProfili): Intent {
            return Intent(context, SecondActivity::class.java).apply {
                putExtra(EXTRA_ISIM, profil.isim)
                putExtra(EXTRA_UNVAN, profil.unvan)
                putExtra(EXTRA_GITHUB_URL, profil.githubUrl)
                putExtra(EXTRA_TECRUBE, profil.tecrubeYili)
            }
        }
    }
}
