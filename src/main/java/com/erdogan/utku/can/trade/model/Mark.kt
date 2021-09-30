package com.erdogan.utku.can.trade.model

import javax.persistence.*

@Entity
data class Mark(
        @Id
        val id : Long,
        val name : String,

        @OneToOne(mappedBy = "mark", fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        val advert: Advert
)
