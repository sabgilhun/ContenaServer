package com.sabgil.contena.entitiy

import javax.persistence.*

@Entity(name = "recommend")
class RecommendEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,

        @ManyToOne
        @JoinColumn(name = "shop_name")
        var shopEntity: ShopEntity? = null
)