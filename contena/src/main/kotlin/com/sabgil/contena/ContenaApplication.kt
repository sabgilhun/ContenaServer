package com.sabgil.contena

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ContenaApplication

fun main(args: Array<String>) {
    runApplication<ContenaApplication>(*args)
}
