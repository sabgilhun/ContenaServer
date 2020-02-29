package com.sabgil.contena.entitiy

import javax.persistence.*

@Entity(name = "shop")
class ShopEntity(
        @Id
        @Column(name = "shop_name")
        var shopName: String = "",

        @Column(name = "shop_logo_url")
        var shopLogoUrl: String = "",

        @Column(name = "subscriber_count")
        var subscriberCount: Long = 0,

        @OneToMany(mappedBy = "shopEntity")
        var postEntity: List<PostEntity>? = null,

        @OneToMany(mappedBy = "shopEntity")
        var subscriptionEntities: List<SubscriptionEntity> = emptyList()
)