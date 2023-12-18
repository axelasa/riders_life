package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserRolesEntity
import java.util.*

interface RoleService {
    fun createRole(role:String): UserRolesEntity

    fun getRole(roleName:String): Optional<UserRolesEntity>

    fun defaultRoles()
}