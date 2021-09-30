package com.erdogan.utku.can.trade.model

import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Advert(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id : Long?,
        val productName : String?,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "category_id", nullable = false)
        val category: Category,

        @OneToOne(cascade = [CascadeType.ALL])
        @JoinColumn(name = "mark_id", nullable = false)
        val mark: Mark,
        val description : String?,
        val price : BigDecimal?,
        val advertType : AdvertType?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "user_id", nullable = false)
        val user : User,
        @OneToMany(mappedBy = "advert", fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
        val comments : Set<Comment>
){
    constructor(productName: String?,category: Category,mark: Mark, description: String?,
                  price: BigDecimal?, advertType : AdvertType?, user: User) : this(
            0,
            productName = productName,
            category = category,
            mark = mark,
            description = description,
            price = price,
            advertType = advertType,
            user = user,
            HashSet()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Advert

        if (id != other.id) return false
        if (productName != other.productName) return false
        if (description != other.description) return false
        if (price != other.price) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (productName?.hashCode() ?: 0)
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (price?.hashCode() ?: 0)
        result = 31 * result + user.hashCode()
        return result
    }


}

enum class AdvertType{
      ACTIVE, SOLD, INVALID
}
