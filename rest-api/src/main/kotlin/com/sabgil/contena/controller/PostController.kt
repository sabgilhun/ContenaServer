package com.sabgil.contena.controller

import com.sabgil.contena.exceptiom.BadRequestException
import com.sabgil.contena.repository.PostRepository
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
            throw BadRequestException("유저 정보가 잘못 되었습니다.")
        }

        val shopEntities = subscriptionRepository.findByUserId(userId).mapNotNull {
            it.shopEntity
        }

        val postEntities = if (shopEntities.isNotEmpty()) {
            if (cursor == -1L) {
                postRepository.findTop20ByShopEntityInOrderByIdDesc(shopEntities)
            } else {
                postRepository.findTop20ByIdBeforeAndShopEntityInOrderByIdDesc(cursor, shopEntities)
            }
        } else {
            emptyList()
        }

        return GetPostListResponse.from(postEntities)
    }
}
