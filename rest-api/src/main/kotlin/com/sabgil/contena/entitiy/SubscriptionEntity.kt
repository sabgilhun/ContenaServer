package com.sabgil.contena.entitiy

import javax.persistence.*

@Entity(name = "subscription")
class SubscriptionEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "user_id")
        var userId: String = "",

        @OneToOne
        @JoinColumn(name = "shop_name")
        var shopEntity: ShopEntity? = null
)