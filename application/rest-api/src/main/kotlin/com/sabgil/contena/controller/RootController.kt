package com.sabgil.contena.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RootController {

    @GetMapping("/")
    fun root(): String {
        return "hello contena"
    }
}
