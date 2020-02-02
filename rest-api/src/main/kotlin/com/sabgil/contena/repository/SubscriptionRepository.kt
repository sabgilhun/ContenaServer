package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.ShopEntity
import com.sabgil.contena.entitiy.SubscriptionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionRepository : JpaRepository<SubscriptionEntity, Long> {

    fun findByUserId(userId: String): List<SubscriptionEntity>

    fun findByUserIdAndShopEntity(userId: String, shopEntity: ShopEntity): List<SubscriptionEntity>

}