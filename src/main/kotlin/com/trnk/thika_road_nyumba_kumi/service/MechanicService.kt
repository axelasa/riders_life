package com.trnk.thika_road_nyumba_kumi.service

import com.trnk.thika_road_nyumba_kumi.entities.MechanicEntity
import com.trnk.thika_road_nyumba_kumi.model.MechanicModel
import com.trnk.thika_road_nyumba_kumi.model.UpdateMechanic
import java.util.*


interface MechanicService {
    fun createMechanic(mechanic:MechanicModel):MechanicEntity
    fun updateMechanic(mechanic: UpdateMechanic):MechanicEntity
    fun getMechanicByName(username:String):Optional<MechanicEntity>
    fun getAllMechanics():List<MechanicEntity>
}