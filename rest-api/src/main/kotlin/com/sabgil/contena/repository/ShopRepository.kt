package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional


@Repository
interface ShopRepository : JpaRepository<ShopEntity, String> {

    fun findByShopNameContaining(shopNameKeyword: String): List<ShopEntity>

    @Transactional
    @Modifying
    @Query("UPDATE shop s set s.subscriberCount = s.subscriberCount + 1 where s.shopName = :shopName")
    fun updateIncreaseSubscriberCount(@Param("shopName") shopName: String)

    @Transactional
    @Modifying
    @Query("UPDATE shop s set s.subscriberCount = s.subscriberCount - 1 where s.shopName = :shopName")
    fun updateDecreaseSubscriberCount(@Param("shopName") shopName: String)
}