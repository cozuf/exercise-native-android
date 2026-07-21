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

data class Kullanici(
    val id: Int,
    val isim: String,
    val eposta: String,
    val aktifMi: Boolean = true // Varsayılan değer atanabilir
)

data class Ogrenci(
    val id: Int,
    val ad: String,
    val notOrtalamasi: Double? // Null gelebilir
)

class MainActivity : ComponentActivity() {

    // Logları filtrelemek için kullanacağımız benzersiz bir etiket (TAG)
    private val TAG = "AndroidKampLog"

    // 1. EKRAN İLK KEZ OLUŞTURULURKEN (Dün yazdığımız yer)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "LIFECYCLE: onCreate tetiklendi! Ekran belleğe yüklendi.")

        val isim: String = "Yusuf"
        // isim = "Ahmet" // DERLEME HATASI! val tekrar atanamaz.

        var sayac: Int = 0
        sayac = 1 // Geçerli

        var mesaj: String = "Merhaba"
        // mesaj = null // DERLEME HATASI! Kotlin izin vermez.

        // Null olabilecek bir değişken için tipin sonuna '?' koyarız:
        var nullableMesaj: String? = "Merhaba"
        nullableMesaj = null // Geçerli

        // 1. Safe Call Operator (?.): Değişken null ise alt özelliği çağırmaz, direkt null döner (Çökmez).
        Log.d("LOG", nullableMesaj?.length.toString())

        // 2. Elvis Operator (?:) - (TypeScript'teki Nullish Coalescing ?? gibi):
        // "Eğer soldaki değer null ise, sağdaki varsayılan değeri kullan" demektir.
        val uzunluk: Int = nullableMesaj?.length ?: 0

        // 3. Not-Null Assertion (!!):
        // "Bu değer %100 null değil, derleyici sen aradan çekil" demektir.
        // Eğer değişken null çıkarsa uygulama ANINDA ÇÖKER (Crash). Kullanmaktan kaçınmalıyız!
        // val kesinUzunluk: Int = nullableMesaj!!.length

        // notOrtalamasi null olan bir öğrenci oluşturuyoruz
        val ogrenci1 = Ogrenci(id = 1, ad = "Yusuf Coşkun", notOrtalamasi = null)

        // Elvis (?:) operatörü ile null kontrolü yapıyoruz: Not null ise kendisini, null ise 0.0 al.
        val gosterilecekNot = ogrenci1.notOrtalamasi ?: 0.0

        Log.d(TAG, "Öğrenci Adı: ${ogrenci1.ad}, Notu: $gosterilecekNot")

        // copy() fonksiyonu örneği (Sadece belirli alanları değiştirip yeni obje türetme)
        val ogrenci2 = ogrenci1.copy(id = 2, notOrtalamasi = 85.5)
        Log.d(TAG, "Yeni Öğrenci: ${ogrenci2.ad}, Güncellenmiş Notu: ${ogrenci2.notOrtalamasi}")

        // Sadece uygulama ilk kez sıfırdan açıldığında 1 defa çalışır
//        if (savedInstanceState == null) {
//            // Denemek için birini çağırabilirsin:
//            webSitesiAc("https://github.com")
//            // ya da:
//            // metinPaylas("Android kampında 4. günü tamamlıyorum!")
//        }
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


//        // --- INTENT BAŞLIYOR ---
//        // Postacıyı hazırlıyoruz: MainActivity'den SecondActivity'ye gideceğini söylüyoruz.
//        val intent = Intent(this, SecondActivity::class.java)
//
//        // Giderken yanına bir veri paketi veriyoruz (Key-Value mantığı)
//        intent.putExtra(SecondActivity.EXTRA_KULLANICI_ADI, "Yusuf Coşkun")
//        intent.putExtra(SecondActivity.EXTRA_KAMP_GUNU, 4)
//
//        // Postacıyı yola çıkarıyoruz, Android sistemi yeni ekranı başlatıyor
//        startActivity(intent)
//        // -----------------------
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

    // 1. WEB SİTESİ AÇMA (ACTION_VIEW)
    fun webSitesiAc(url: String) {
        // Android'e "Bir URI/Link görüntülemek istiyorum" diyoruz
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    // 2. DIŞARIYA METİN PAYLAŞMA (ACTION_SEND)
    fun metinPaylas(mesaj: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, mesaj)
            type = "text/plain"
        }
        // Kullanıcının karşısına sistemin "Şununla Paylaş" menüsünü (Chooser) çıkarır
        val shareIntent = Intent.createChooser(sendIntent, "Mesajı Şununla Paylaş:")
        startActivity(shareIntent)
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