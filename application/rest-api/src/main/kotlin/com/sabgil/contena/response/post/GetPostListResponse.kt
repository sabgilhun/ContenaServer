package com.sabgil.contena.response.post

import com.fasterxml.jackson.annotation.JsonProperty
import com.sabgil.contena.entitiy.ItemEntity
import com.sabgil.contena.entitiy.PostEntity

data class GetPostListResponse(
        @JsonProperty("last_cursor") val lastCursor: Long,
        @JsonProperty("post_list") val postList: List<Post>
) {
    data class Post(
            @JsonProperty("post_id") val postId: Long,
            @JsonProperty("upload_data") val uploadDate: String,
            @JsonProperty("shop_name") val shopName: String,
            @JsonProperty("shop_logo_url") val shopLogoUrl: String,
            @JsonProperty("subscriber_count") val subscriberCount: Long,
            @JsonProperty("new_item_list") val newItemList: List<NewItem>
    )

    data class NewItem(
            @JsonProperty("product_name") val productName: String,
            @JsonProperty("brand") val brand: String,
            @JsonProperty("image_url") val imageUrl: String,
            @JsonProperty("page_url") val pageUrl: String,
            @JsonProperty("price") val price: String,
            @JsonProperty("origin_price") val originPrice: String?
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
                subscriberCount = shopEntity?.subscriptionEntities?.size?.toLong() ?: 0L,
                newItemList = itemEntities.map { itemEntity -> itemEntity.mapToNewItem() }
        )

        private fun ItemEntity.mapToNewItem() = NewItem(
                productName = productName,
                brand = brand,
                imageUrl = imageUrl,
                pageUrl = pageUrl,
                price = price,
                originPrice = originPrice
        )
    }
}