package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import com.trnk.thika_road_nyumba_kumi.model.UserModel
import com.trnk.thika_road_nyumba_kumi.repos.UserRepo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class UserServiceImpl(private val userRepo: UserRepo) : UserService {
        
    override fun createUser(user: UserModel): UserEntity {
        val existingUser = getUserByIdNumber(user.idNumber)
        if (existingUser.isPresent) throw ControllerExceptionHandler.conflicts("This user Already Exists")
        val newUser = UserEntity.createNewUser(user)
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

    override fun getAllUsers(): List<UserEntity> {
       return userRepo.findAll()
    }

}