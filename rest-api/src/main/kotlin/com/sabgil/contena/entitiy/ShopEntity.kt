package com.sabgil.contena.entitiy

import javax.persistence.*

@Entity
class ShopEntity(
        @Id
        @Column(name = "shop_name")
        var shopName: String = "",

        @Column(name = "shop_logo_url")
        var shopLogoUrl: String = "",

        @Column(name = "subscriber_count")
        var subscriberCount: Long = 0,

        @OneToOne(mappedBy = "shopEntity")
        var postEntity: PostEntity? = null
)