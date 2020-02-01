package com.sabgil.contena.entitiy

import javax.persistence.*

@Entity
class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        @OneToOne
        @JoinColumn(name = "shop_name")
        var shop: Shop? = null,

        @Column(name = "upload_date")
        var uploadDate: String = "",

        @OneToMany(mappedBy = "post")
        var newItems: List<Item>? = null
)