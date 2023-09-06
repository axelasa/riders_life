package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.EmergencyContactEntity
import com.trnk.thika_road_nyumba_kumi.exceptions.ControllerExceptionHandler
import com.trnk.thika_road_nyumba_kumi.model.EmergencyContactModel
import com.trnk.thika_road_nyumba_kumi.model.EmergencyContactUpdate
import com.trnk.thika_road_nyumba_kumi.repos.EmergencyContactRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class EmergencyContactServiceImpl(private val emergencyContactRepository: EmergencyContactRepository,private val userService: UserService) : EmergencyContactService {
    override fun createEmergencyContacts(emergencyContactModel: EmergencyContactModel): EmergencyContactEntity {
       val existingContact = getEmergencyContactByRelations(emergencyContactModel.relations)
        if (existingContact.isPresent)throw ControllerExceptionHandler.conflicts("This Contact ${emergencyContactModel.phoneNumber} Already Exists")

        val optionalUser = userService.getUserByIdNumber(emergencyContactModel.idNumber)

        if (optionalUser.isEmpty) throw ControllerExceptionHandler.conflicts("This User ${emergencyContactModel.idNumber} Does Not Exist")

        val user = optionalUser.get()

        val newContact = EmergencyContactEntity.createEmergencyContact(emergencyContactModel,user)

        return emergencyContactRepository.save(newContact)
    }

    override fun updateEmergencyContacts(emergencyContactUpdate: EmergencyContactUpdate): EmergencyContactEntity {
        val existingEmergencyContact = getEmergencyContactByRelations(emergencyContactUpdate.relations)
        when {
            existingEmergencyContact.isEmpty -> throw ControllerExceptionHandler.notFound("This profile data does not exist")
            else -> {
                val updateExistingEmergencyContact = existingEmergencyContact.get()

                if (emergencyContactUpdate.firstname != null) updateExistingEmergencyContact.firstname =
                    emergencyContactUpdate.firstname
                if (emergencyContactUpdate.lastname != null) updateExistingEmergencyContact.lastname =
                    emergencyContactUpdate.lastname
                if (emergencyContactUpdate.phoneNumber != null) updateExistingEmergencyContact.phoneNumber =
                    emergencyContactUpdate.phoneNumber
                updateExistingEmergencyContact.updatedAt = Date()
                return  emergencyContactRepository.save(updateExistingEmergencyContact)
            }
        }
    }
    override fun getEmergencyContactByLastName(lastname: String, pageable: Pageable): Page<EmergencyContactEntity> {
       return emergencyContactRepository.findByLastname(lastname,pageable)
    }

    override fun getEmergencyContactByRelations(relation: String): Optional<EmergencyContactEntity> {
        return emergencyContactRepository.findByRelations(relation)
    }

    override fun getEmergencyContactById(id: Long): Optional<EmergencyContactEntity> {
        return emergencyContactRepository.findById(id)
    }

    override fun getAllEmergencyContacts(): List<EmergencyContactEntity> {
        return emergencyContactRepository.findAll()
    }
}