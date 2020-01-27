package com.sabgil.contena.controller

import com.sabgil.contena.repository.PostRepositoryImpl
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController {
    @RequestMapping("/")
    fun home(): String {
        PostRepositoryImpl().getPosts(0)
        return "ss"
    }
}
