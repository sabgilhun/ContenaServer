package com.sabgil.contena.response.post

import com.sabgil.contena.entitiy.ItemEntity
import com.sabgil.contena.entitiy.PostEntity

data class GetPostListResponse(
        val lastCursor: Long,
        val postList: List<Post>
) {
    data class Post(
            val postId: Long,
            val uploadDate: String,
            val shopName: String,
            val shopLogoUrl: String,
            val newItemList: List<NewItem>
    )

    data class NewItem(
            val productName: String,
            val brand: String,
            val imageUrl: String,
            val price: String
    )

    companion object {
        fun from(postEntities: List<PostEntity>): GetPostListResponse {
            return if (postEntities.isEmpty()) {
                GetPostListResponse(lastCursor = -1, postList = emptyList())
            } else {
                GetPostListResponse(
                        lastCursor = postEntities.last().id,
                        postList = postEntities.map { it.mapToPost() }
                )
            }
        }

        private fun PostEntity.mapToPost() = Post(
                postId = id,
                uploadDate = uploadDate,
                shopName = shopEntity?.shopName ?: "",
                shopLogoUrl = shopEntity?.shopLogoUrl ?: "",
                newItemList = itemEntities?.subList(0, 5)?.map { it.mapToNewItem() } ?: emptyList()
        )

        private fun ItemEntity.mapToNewItem() = NewItem(
                productName = productName,
                brand = brand,
                imageUrl = imageUrl,
                price = price
        )
    }
}