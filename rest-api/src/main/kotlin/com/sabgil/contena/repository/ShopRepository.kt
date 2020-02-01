package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.Post
import com.sabgil.contena.entitiy.Shop
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShopRepository : JpaRepository<Shop, String>