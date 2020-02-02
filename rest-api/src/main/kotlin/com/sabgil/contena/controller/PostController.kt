package com.sabgil.contena.controller

import com.sabgil.contena.entitiy.PostEntity
import com.sabgil.contena.repository.ItemRepository
import com.sabgil.contena.repository.PostRepository
import com.sabgil.contena.repository.ShopRepository
import com.sabgil.contena.repository.SubscriptionRepository
import com.sabgil.contena.response.post.GetPostListResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PostController(
        private val postRepository: PostRepository,
        private val subscriptionRepository: SubscriptionRepository
) {

    @GetMapping("/post")
    fun getPostList(
            @RequestParam(value = "user_id", defaultValue = "") userId: String,
            @RequestParam(value = "cursor", defaultValue = "-1") cursor: Long
    ): GetPostListResponse {
        if (userId.isEmpty()) {
            throw Exception()
        }

        val postEntities = subscriptionRepository.findByUserId(userId).fold(mutableListOf<PostEntity>())
        { postEntities, subscriptionEntity ->
            postEntities.apply {
                addAll(subscriptionEntity.shopEntity?.let
                {
                    postRepository.findByShopEntityOrderByIdDesc(it)
                } ?: emptyList())
            }
        }

        return GetPostListResponse.from(postEntities)
    }
}
