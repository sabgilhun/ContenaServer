package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {

    fun findByShopName(shopName: String): List<Post>
}