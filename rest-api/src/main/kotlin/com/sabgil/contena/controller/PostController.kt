package com.sabgil.contena.controller

import com.sabgil.contena.repository.ItemRepository
import com.sabgil.contena.repository.PostRepository
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.response.post.GetPostListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController("/post")
class PostController(
        private val itemRepository: ItemRepository,
        private val postRepository: PostRepository,
        private val shopRepository: ShopRepository
) {

    @GetMapping("/post")
    fun getPostList(
            @RequestParam(value = "userId", defaultValue = "") userId: String,
            @RequestParam(value = "cursor", defaultValue = "0") cursor: Long
    ): GetPostListResponse {
        TODO()
    }
}
