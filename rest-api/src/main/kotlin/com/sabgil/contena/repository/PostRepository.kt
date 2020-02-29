package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.PostEntity
import com.sabgil.contena.entitiy.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long> {

    fun findTop20ByShopEntityInOrderByIdDesc(shopEntities: List<ShopEntity>): List<PostEntity>

    fun findTop20ByIdBeforeAndShopEntityInOrderByIdDesc(id: Long, shopEntities: List<ShopEntity>): List<PostEntity>
}