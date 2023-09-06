package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.MechanicEntity
import com.trnk.thika_road_nyumba_kumi.model.MechanicModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMechanic
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.util.*


interface MechanicService {
    fun createMechanic(mechanic:MechanicModel):MechanicEntity
    fun updateMechanic(mechanic: UpdateMechanic):MechanicEntity
    fun getMechanicByName(username:String):Optional<MechanicEntity>
    fun searchMechanicLocationsWithinDistance(targetLat: Double, targetLon: Double, distance:Int,pageRequest: PageRequest):List<MechanicEntity>
    fun getAllMechanics():List<MechanicEntity>
}