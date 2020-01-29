package com.sabgil.contena.entitiy

import javax.persistence.*
import javax.validation.constraints.NotBlank


@Entity
data class Item(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "shop_name")
        @get: NotBlank
        var shopName: String = "",

        @Column(name = "product_name")
        @get: NotBlank
        var productName: String = "",

        @Column(name = "brand")
        @get: NotBlank
        var brand: String = "",

        @Column(name = "image_url")
        @get: NotBlank
        var imageUrl: String = "",

        @Column(name = "page_url")
        @get: NotBlank
        var pageUrl: String = "",

        @Column(name = "price")
        @get: NotBlank
        var price: String = "",

        @ManyToOne
        @get: NotBlank
        @JoinColumn(name = "post_id")
        var post: Post? = null
) {
    override fun toString(): String {
        return "Item(id=$id, shopName='$shopName', productName='$productName'," +
                " brand='$brand', imageUrl='$imageUrl', pageUrl='$pageUrl', price='$price')"
    }
}


