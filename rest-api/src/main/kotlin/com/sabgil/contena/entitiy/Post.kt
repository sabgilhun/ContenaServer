package com.sabgil.contena.entitiy

import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
data class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @Column(name = "shop_name")
        @get: NotBlank
        var shopName: String = "",

        @Column(name = "shop_logo_url")
        @get: NotBlank
        var shopLogoUrl: String = "",

        @Column(name = "upload_date")
        @get: NotBlank
        var uploadDate: String = "",

        @OneToMany(mappedBy = "post")
        var newItems: List<Item>? = null
)