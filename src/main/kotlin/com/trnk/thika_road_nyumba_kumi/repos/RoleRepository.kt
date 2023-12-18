package com.trnk.thika_road_nyumba_kumi.repos

import com.trnk.thika_road_nyumba_kumi.entities.UserRolesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface RoleRepository: JpaRepository<UserRolesEntity, Long> {
    fun findByName(name: String): Optional<UserRolesEntity>
}