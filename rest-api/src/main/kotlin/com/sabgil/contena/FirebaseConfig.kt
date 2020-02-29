package com.sabgil.contena

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.FileInputStream
import java.io.IOException


@Configuration
class FirebaseConfig {
    @Bean
    @Throws(IOException::class)
    internal fun createFireBaseApp(): FirebaseApp {
        val serviceAccount = FileInputStream("./firebase_key.json")

        val options = FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://contena-5c99b.firebaseio.com")
                .build()

        return FirebaseApp.initializeApp(options)
    }
}