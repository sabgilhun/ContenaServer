package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.PostEntity
import com.sabgil.contena.repository.PostRepository
import com.sabgil.contena.response.newitem.GetNewItemListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class NewItemController(
        private val postRepository: PostRepository
) {

    @GetMapping("/new_item")
    fun getNewItemList(
            @RequestParam(value = "post_id", defaultValue = "-1") postId: Long
    ): GetNewItemListResponse {
        if (postId < 0) {
            throw Exception()
        }

        var postEntity: PostEntity? = null
        postRepository.findById(postId).ifPresent { postEntity = it }

        return GetNewItemListResponse.from(postEntity ?: throw Exception())
    }
}
