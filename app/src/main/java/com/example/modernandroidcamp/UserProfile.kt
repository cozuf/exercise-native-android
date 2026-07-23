package com.example.modernandroidcamp

/**
 * Senior Pratik: Model sınıfları uygulamanın her yerinde yeniden kullanılabilir (reusable)
 * olması için bağımsız dosyalarda tutulur.
 */
data class KullaniciProfili(
    val id: Int,
    val isim: String,
    val unvan: String,
    val githubUrl: String?, // Null gelebilme ihtimaline karşı nullable yapıyoruz
    val tecrubeYili: Int
)