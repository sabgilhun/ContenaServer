package com.sabgil.contena.response.newitem

import com.sabgil.contena.entitiy.ItemEntity
import com.sabgil.contena.entitiy.PostEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

data class GetNewItemListResponse(
        val newItemList: List<NewItem>
) {
    data class NewItem(
            val productName: String,
            val brand: String,
            val imageUrl: String,
            val pageUrl: String,
            val price: String
    )

    companion object {
        fun from(postEntity: PostEntity): GetNewItemListResponse {
            val itemEntities = postEntity.itemEntities

            if (itemEntities.isNullOrEmpty()) {
                throw EmptyPostException()
            } else {
                return GetNewItemListResponse(newItemList = itemEntities.map { it.mapToNewItem() })
            }
        }

        private fun ItemEntity.mapToNewItem() = NewItem(
                productName = productName,
                brand = brand,
                imageUrl = imageUrl,
                pageUrl = pageUrl,
                price = price
        )
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    class EmptyPostException : RuntimeException("포스트에 상품이 존재 하지 안습니다.")
}