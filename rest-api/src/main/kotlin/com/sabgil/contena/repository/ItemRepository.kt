package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ItemRepository : JpaRepository<ItemEntity, Long> {

    fun findByShopName(shopName: String): List<ItemEntity>
}