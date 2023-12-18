package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import com.trnk.thika_road_nyumba_kumi.enums.Roles
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import com.trnk.thika_road_nyumba_kumi.model.UpdateUserModel
import com.trnk.thika_road_nyumba_kumi.model.UserModel
import com.trnk.thika_road_nyumba_kumi.repos.UserRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserServiceImpl(private val userRepo: UserRepo,var roleService: RoleService) : UserService {


    override fun createUser(user: UserModel): UserEntity {

        val optionalRole = roleService.getRole(Roles.USER.roles)
        if (optionalRole.isEmpty) throw ControllerExceptionHandler.notFound("This Role Does not Exist")

        val existingUser = getUserByIdNumber(user.idNumber)
        if (existingUser.isPresent) throw ControllerExceptionHandler.conflicts("This user Already Exists")
        val newUser = UserEntity.createNewUser(user,optionalRole.get())
        return userRepo.save(newUser)
    }

    override fun searchUserByIdNumber(idNumber: String, pageable: Pageable): Page<UserEntity> {
        return userRepo.findByIdNumberLike(idNumber,pageable)
    }

    override fun getUserByIdNumber(idNumber: String): Optional<UserEntity> {
        return userRepo.findByIdNumber(idNumber)
    }

    override fun getUserById(id: Long): Optional<UserEntity> {
        return userRepo.findById(id)
    }

    override fun updateUser(userDetails: UpdateUserModel): UserEntity {
        val existingUser = getUserByIdNumber(userDetails.idNumber);
        if (existingUser.isEmpty) throw  RuntimeException("This User Does Not Exist")

        val updateUser = existingUser.get()

        when {
            userDetails.firstname != null -> updateUser.firstname = userDetails.firstname
            userDetails.lastname != null -> updateUser.lastname = userDetails.lastname
            userDetails.password != null -> updateUser.userPassword = userDetails.password
        }
//        if (userDetails.lastname != null )  updateUser.lastname = userDetails.lastname
//        if (userDetails.password != null)  updateUser.password = userDetails.password
        updateUser.updatedAt = Date()

        return userRepo.save(updateUser)
    }

    override fun getAllUsers(): List<UserEntity> {
       return userRepo.findAll()
    }



}