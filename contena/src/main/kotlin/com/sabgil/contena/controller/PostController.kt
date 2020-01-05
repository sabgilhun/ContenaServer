package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.TestModel
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {
    @RequestMapping("/")
    fun home() = TestModel("hello", " world")
}
