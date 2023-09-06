package com.trnk.thika_road_nyumba_kumi.repos

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepo:JpaRepository<UserEntity,Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM user_data WHERE id_number LIKE %:idNumber%")
    fun findByIdNumberLike(idNumber:String,pageable: Pageable): Page<UserEntity>
    fun findByIdNumber(idNumber: String):Optional<UserEntity>
    override fun findById(id:Long): Optional<UserEntity>
}