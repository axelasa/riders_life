package com.trnk.thika_road_nyumba_kumi.specification

import com.trnk.thika_road_nyumba_kumi.entities.EmergencyContactEntity
import com.trnk.thika_road_nyumba_kumi.entities.UserEntity
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification

class EmergencyContactSpecification(private val idNumber:String,private val contactId:Long):Specification<EmergencyContactEntity> {
    private  lateinit var r:Root<EmergencyContactEntity>
    private lateinit var cb: CriteriaBuilder
    private lateinit var p:Predicate
    override fun toPredicate(
        root: Root<EmergencyContactEntity>,
        query: CriteriaQuery<*>,
        criteriaBuilder: CriteriaBuilder
    ): Predicate {
        r = root
        cb = criteriaBuilder
        p = criteriaBuilder.and()

        addId(contactId)
        addIdNumber(idNumber)

        return p
    }

    private fun addId(contactId: Long?){
        if (contactId == null) return
        p.expressions.add(cb.equal(r.get<Long>("id"),contactId))
    }
    private fun addIdNumber(idNumber: String?){
        if (idNumber == null) return
        p.expressions.add(cb.equal(cb.upper(r.get<UserEntity>("user_data").get("idNumber")),idNumber))
    }
}