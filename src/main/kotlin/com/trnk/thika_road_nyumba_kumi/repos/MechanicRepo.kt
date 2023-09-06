package com.trnk.thika_road_nyumba_kumi.repos

import com.trnk.thika_road_nyumba_kumi.entities.MechanicEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MechanicRepo:JpaRepository<MechanicEntity,Long> {
fun findByUsername(username: String): Optional<MechanicEntity>
}