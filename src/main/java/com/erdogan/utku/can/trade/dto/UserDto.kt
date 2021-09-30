package com.erdogan.utku.can.trade.dto

data class UserDto(
        val mail : String?,
        val name : String?,
        val surname : String?,
        val userDetails : Set<UserDetailsDto>,
        val adverts : Set<AdvertDto>
){
    constructor(mail: String?, name: String?,surname: String?) : this(
            mail = mail,
            name = name,
            surname = surname,
            HashSet(),
            HashSet()
    )
}
