package com.trnk.thika_road_nyumba_kumi.enums

import java.util.*
enum class Roles(val roles:String) {
    USER("User");
    //ADMIN("Admin");
    companion object {
        @JvmStatic
        fun findByName(name:String): Optional<Roles>{
            for (role in Roles.values()){
                if (role.roles.equals(other = name, ignoreCase = true)) {
                    return Optional.of(role)
                }
            }
            return Optional.empty()
        }
    }
}