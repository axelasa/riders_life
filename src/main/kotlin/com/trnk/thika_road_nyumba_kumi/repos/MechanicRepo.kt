package com.trnk.thika_road_nyumba_kumi.repos

import com.trnk.thika_road_nyumba_kumi.entities.MechanicEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface MechanicRepo:JpaRepository<MechanicEntity,Long> {
fun findByUsername(username: String): Optional<MechanicEntity>
    @Query(
        value = "SELECT * FROM mechanics WHERE latitude = :latitude AND longitude = :longitude",
        nativeQuery = true,

    )

fun findByLongitudeAndLatitude(longitude: Double, latitude: Double,pageable: Pageable): Page<MechanicEntity>



    @Query(
        value = """
            SELECT *, 
            (6371 * 2 * ASIN(SQRT(POWER(SIN((:targetLat - latitude) * 3.141592653589793 / 180 / 2), 2) + COS(:targetLat * 3.141592653589793 / 180) * COS(latitude * 3.141592653589793 / 180) * POWER(SIN((:targetLon - longitude) * 3.141592653589793 / 180 / 2), 2)))) AS distance
            FROM mechanics
            HAVING distance < :distance
            LIMIT :limit OFFSET :offset
        """,
        nativeQuery = true
    )
    fun findLocationsWithinDistance(targetLat: Double, targetLon: Double, distance:Int,limit:Int,offset:Int): List<MechanicEntity>

}