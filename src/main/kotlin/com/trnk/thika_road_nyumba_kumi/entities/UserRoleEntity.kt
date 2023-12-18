package com.trnk.thika_road_nyumba_kumi.entities

import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@Entity
@Table(name = "role")
 class UserRolesEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    val id: Long?,
    @Column(name = "role_name", unique = true, nullable = false)
    @field:NotEmpty
    @field:NotNull
    val name: String
) {
   companion object {
      @JvmStatic
      fun newRoles(roleName: String): UserRolesEntity {
         val roleType = UserRolesEntity(null, roleName)
         return roleType
      }
   }
}