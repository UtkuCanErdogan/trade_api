package com.erdogan.utku.can.trade.model

import javax.persistence.*

@Entity
data class Comment(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long,
        val message : String,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "advert_id", nullable = false)
        val advert : Advert,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user : User
){
    constructor(message: String, advert: Advert, user: User) : this(
            0,
            message = message,
            advert = advert,
            user = user
    )
}
