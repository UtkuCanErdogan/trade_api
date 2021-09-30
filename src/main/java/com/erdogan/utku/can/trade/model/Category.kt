package com.erdogan.utku.can.trade.model

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Category(
        @Id
        val id : Long?,
        val name : String?,

        @OneToOne(mappedBy = "category")
        val advert: Advert
)
