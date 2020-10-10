package com.sabgil.contena.controller

import com.sabgil.contena.exceptiom.BadRequestException
import com.sabgil.contena.exceptiom.NotFoundException
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
            @RequestParam(value = "post_id", defaultValue = "0") postId: Long
    ): GetNewItemListResponse {
        if (postId < 1) {
            throw BadRequestException("잘못된 포스트 번호($postId) 입니다.")
        }

        return GetNewItemListResponse.from(postRepository.findById(postId).orElseThrow {
            return@orElseThrow NotFoundException("존재하지 않는 포스트 번호($postId) 입니다.")
        })
    }
}
