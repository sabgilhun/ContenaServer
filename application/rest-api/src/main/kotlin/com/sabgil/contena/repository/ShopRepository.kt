package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface ShopRepository : JpaRepository<ShopEntity, String> {

    fun findByShopName(shopName: String): ShopEntity?

    fun findByShopNameContaining(shopNameKeyword: String): List<ShopEntity>
}