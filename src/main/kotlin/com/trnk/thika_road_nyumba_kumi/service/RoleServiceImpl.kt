package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserRolesEntity
import com.trnk.thika_road_nyumba_kumi.enums.Roles
import com.trnk.thika_road_nyumba_kumi.repos.RoleRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RoleServiceImpl(private  val roleRepo: RoleRepository) : RoleService {
    override fun createRole(role: String): UserRolesEntity {
        val existingRole = getRole(role)
        if (existingRole.isPresent) throw RuntimeException("Role Already Exists")
        val roles = UserRolesEntity.newRoles(role)
        return roleRepo.save(roles)
    }

    override fun getRole(roleName: String): Optional<UserRolesEntity> {
        return roleRepo.findByName(roleName)
    }

    override fun defaultRoles() {
        for (role in Roles.values()) {
            if (getRole(role.roles).isEmpty) {
                createRole(role.roles)
            }
        }
    }
}