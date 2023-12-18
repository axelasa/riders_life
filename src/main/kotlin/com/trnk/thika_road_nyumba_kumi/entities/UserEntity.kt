package com.trnk.thika_road_nyumba_kumi.entities

import com.trnk.thika_road_nyumba_kumi.model.UserModel
import javax.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*


@Entity
@Table(name = "user_data")
 class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id:Long?,
    val createdAt:Date,
    var firstname:String,
    var lastname:String,
    val idNumber:String,
    var userPassword:String,
    @ManyToOne(fetch = FetchType.EAGER)
    val role: UserRolesEntity,
    @JoinColumn(name = "profile_id")
    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    var profile:ProfileEntity?,
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true )
    val emergencyContact:MutableSet<EmergencyContactEntity> = mutableSetOf(),
    var updatedAt:Date,

    ):UserDetails{
    companion object{
        private val now = Date()
        fun createNewUser(user: UserModel,role: UserRolesEntity):UserEntity{
            val newUser = UserEntity(null, createdAt = now, firstname = user.firstname, lastname = user.lastname, idNumber = user.idNumber, userPassword = user.userPassword, updatedAt = now, emergencyContact = mutableSetOf(), role = role , profile = null,)
            return newUser
        }
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableSetOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return userPassword
    }

    override fun getUsername(): String {
        return idNumber
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
