package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.PostEntity
import com.sabgil.contena.entitiy.ShopEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<PostEntity, Long> {

    fun findByShopEntity(shopEntity: ShopEntity): List<PostEntity>
}