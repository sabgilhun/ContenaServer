package com.sabgil.contena.entitiy

import javax.persistence.*

@Entity(name = "post")
class PostEntity(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @ManyToOne
        @JoinColumn(name = "shop_name")
        var shopEntity: ShopEntity? = null,

        @Column(name = "upload_date")
        var uploadDate: String = "",

        @OneToMany(mappedBy = "postEntity")
        var itemEntities: List<ItemEntity>? = null
)