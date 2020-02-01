package com.sabgil.contena.entitiy

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity(name = "shop")
class ShopEntity(
        @Id
        @Column(name = "shop_name")
        var shopName: String = "",

        @Column(name = "shop_logo_url")
        var shopLogoUrl: String = "",

        @Column(name = "subscriber_count")
        var subscriberCount: Long = 0,

        @OneToOne(mappedBy = "shopEntity")
        var postEntity: PostEntity? = null,

        @OneToOne(mappedBy = "shopEntity")
        var subscriptionEntity: SubscriptionEntity? = null
)