package com.trnk.thika_road_nyumba_kumi.repos

import com.trnk.thika_road_nyumba_kumi.entities.EmergencyContactEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface EmergencyContactRepository :JpaRepository<EmergencyContactEntity,Long>{
    @Query(nativeQuery = true, value = "SELECT * FROM emergency_contact where lastname like %:name%")
    fun findByLastname(name: String,pageable: Pageable): Page<EmergencyContactEntity>
    fun findByRelations(relation:String): Optional<EmergencyContactEntity>
    fun findByIdNumber(idNumber:String): Optional<EmergencyContactEntity>
    override fun findById(id:Long):Optional<EmergencyContactEntity>
}