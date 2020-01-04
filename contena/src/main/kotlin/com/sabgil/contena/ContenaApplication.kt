package com.sabgil.contena

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class ContenaApplication {

    @RequestMapping("/")
    fun home(): String {
        return "hello docker world"
    }
}

fun main(args: Array<String>) {
    runApplication<ContenaApplication>(*args)
}
