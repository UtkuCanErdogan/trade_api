package com.erdogan.utku.can.trade.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long?,

        @Column(unique = true)
        val mail : String?,
        val name : String?,
        val surname : String?,
        val isActive : Boolean?,

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = [CascadeType.ALL])
        val userDetails : Set<UserDetails> = HashSet(),

        @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val adverts : Set<Advert> = HashSet(),

        ){
        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as User

                if (id != other.id) return false
                if (mail != other.mail) return false
                if (name != other.name) return false
                if (surname != other.surname) return false
                if (isActive != other.isActive) return false
                if (userDetails != other.userDetails) return false
                if (adverts != other.adverts) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + mail.hashCode()
                result = 31 * result + name.hashCode()
                result = 31 * result + surname.hashCode()
                result = 31 * result + isActive.hashCode()
                result = 31 * result + userDetails.hashCode()
                result = 31 * result + adverts.hashCode()
                return result
        }

        constructor(mail: String?, name: String?, surname: String?, isActive: Boolean?) :this(
                0,
                mail = mail,
                name = name,
                surname = surname,
                isActive = isActive
                )

        constructor(id: Long?,mail: String?, name: String?, surname: String?, isActive: Boolean?) : this(
                id = id,
                mail = mail,
                name = name,
                surname = surname,
                isActive = isActive,
                HashSet()
        )
}