package com.sabgil.contena.repository

import com.sabgil.contena.entitiy.ReportEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ReportRepository : JpaRepository<ReportEntity, ReportEntity.ReportKey>