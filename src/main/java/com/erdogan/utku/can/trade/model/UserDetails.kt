package com.erdogan.utku.can.trade.model

import javax.persistence.*

@Entity
data class UserDetails(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long?,
        val phoneNumber : String?,
        val address : String?,
        val country : String?,
        val city : String?,
        val postCode : String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user : User
){
        constructor(phoneNumber: String, address: String, country: String, city: String, postCode: String, user: User) :
                this(
                0,
                phoneNumber = phoneNumber,
                address = address,
                country = country,
                city = city,
                postCode = postCode,
                user = user
        )

        override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (javaClass != other?.javaClass) return false

                other as UserDetails

                if (id != other.id) return false
                if (phoneNumber != other.phoneNumber) return false
                if (address != other.address) return false
                if (country != other.country) return false
                if (city != other.city) return false
                if (postCode != other.postCode) return false
                if (user != other.user) return false

                return true
        }

        override fun hashCode(): Int {
                var result = id.hashCode()
                result = 31 * result + phoneNumber.hashCode()
                result = 31 * result + address.hashCode()
                result = 31 * result + country.hashCode()
                result = 31 * result + city.hashCode()
                result = 31 * result + postCode.hashCode()
                result = 31 * result + user.hashCode()
                return result
        }

}
