package com.sabgil.contena.entitiy

import javax.persistence.*

@Entity
class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "shop_name")
        var shopName: String = "",

        @Column(name = "shop_logo_url")
        var shopLogoUrl: String = "",

        @Column(name = "upload_date")
        var uploadDate: String = "",

        @OneToMany(mappedBy = "post")
        var newItems: List<Item>? = null
)