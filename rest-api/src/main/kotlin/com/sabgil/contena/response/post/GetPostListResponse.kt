package com.sabgil.contena.response.post

import com.sabgil.contena.entitiy.ItemEntity
import com.sabgil.contena.entitiy.PostEntity

data class GetPostListResponse(
        val lastCursor: Long,
        val postList: List<Post>
) {
    data class Post(
            val postId: Long,
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
            return GetPostListResponse(
                    postEntities.last().id,
                    postEntities.map { it.mapToPost() }
            )
        }

        private fun PostEntity.mapToPost() = Post(
                postId = id,
                shopName = shopEntity?.shopName ?: "",
                shopLogoUrl = shopEntity?.shopLogoUrl ?: "",
                newItemList = itemEntities?.map { it.mapToNewItem() } ?: emptyList()
        )

        private fun ItemEntity.mapToNewItem() = NewItem(
                productName = productName,
                brand = brand,
                imageUrl = imageUrl,
                price = price
        )
    }
}