package com.sabgil.contena.entitiy

import javax.persistence.*


@Entity
class ItemEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "shop_name")
        var shopName: String = "",

        @Column(name = "product_name")
        var productName: String = "",

        @Column(name = "brand")
        var brand: String = "",

        @Column(name = "image_url")
        var imageUrl: String = "",

        @Column(name = "page_url")
        var pageUrl: String = "",

        @Column(name = "price")
        var price: String = "",

        @ManyToOne
        @JoinColumn(name = "post_id")
        var postEntity: PostEntity? = null
)
