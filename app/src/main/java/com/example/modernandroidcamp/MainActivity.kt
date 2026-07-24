package com.example.modernandroidcamp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.modernandroidcamp.ui.theme.ModernAndroidCampTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ModernAndroidCampTheme {
                // Surface: Arka plan rengini ve temel temayı sağlayan kapsayıcı
                Surface(color = MaterialTheme.colorScheme.background) {
                    UserCardList()
                }
            }
        }
    }
}

// --- İLK COMPOSABLE BİLEŞENİMİZ ---
@Composable
fun UserCard(name: String, title: String) {
    // Row: React Native'deki flexDirection: 'row'
    Row(modifier = Modifier.padding(16.dp)) {
        // Column: React Native'deki flexDirection: 'column'
        Column {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun UserCardList() {
    Column {
        UserCard(name = "Yusuf Coşkun", title = "Mobile Application Developer")
        UserCard(name = "Ahmet Yılmaz", title = "Android Engineer")
    }
}

// --- CANLI ÖNİZLEME (PREVIEW) ---
// Emülatörü çalıştırmadan sağ taraftaki "Split" veya "Design" sekmesinden arayüzü görmemizi sağlar.
//@Preview(showBackground = true)

//@Preview(
//    showBackground = true,
//    showSystemUi = true, // Tam telefon ekranını ve durum çubuğunu simüle eder
//    name = "Tam Ekran Profil Liste Önizlemesi",
//    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
//)
//@Preview(name = "Dark Mode", showBackground = true, uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES)
// 1. Açık Mod Önizlemesi
@Preview(
    name = "Açık Mod",
    showBackground = true,
    showSystemUi = true
)
// 2. Karanlık Mod Önizlemesi
@Preview(
    name = "Karanlık Mod",
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun PreviewUserCardList() {
    ModernAndroidCampTheme {
        // Dark Mode geçişlerinde arka planın ve yazı renklerinin
        // temaya uyum sağlaması için Surface ile sarmalıyoruz:
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background) {
            UserCardList()
        }
    }
}