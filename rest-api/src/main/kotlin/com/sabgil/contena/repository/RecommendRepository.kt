package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.RecommendEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RecommendRepository : JpaRepository<RecommendEntity, Long>