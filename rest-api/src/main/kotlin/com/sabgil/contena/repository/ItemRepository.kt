package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.Item
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<Item, Long> {

    fun findByShopName(shopName: String): List<Item>
}