package com.sabgil.contena

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.HashMap
import java.io.FileInputStream


@SpringBootApplication
class ContenaApplication

fun main(args: Array<String>) {
    firebaseInit()
    runApplication<ContenaApplication>(*args)
}

fun firebaseInit() {
    val serviceAccount = FileInputStream("firebase_key.json")

    val options = FirebaseOptions.Builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .setDatabaseUrl("https://contena-5c99b.firebaseio.com")
            .build()
    FirebaseApp.initializeApp(options)
}