package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.EmergencyContactEntity
import com.trnk.thika_road_nyumba_kumi.model.EmergencyContactModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.Optional

interface EmergencyContactService {
    fun createEmergencyContacts(emergencyContactModel: EmergencyContactModel):EmergencyContactEntity
    fun getContactByIdNumber(idNumber:String):Optional<EmergencyContactEntity>
    fun getEmergencyContactByLastName(lastname:String,pageable: Pageable):Page<EmergencyContactEntity>
    fun getEmergencyContactByRelations(relation:String):Optional<EmergencyContactEntity>
    fun getEmergencyContactById(id:Long):Optional<EmergencyContactEntity>
    fun getAllEmergencyContacts():List<EmergencyContactEntity>

}