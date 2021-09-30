package com.erdogan.utku.can.trade.dto

data class CreateUserDetailsRequest(
        val userId : Long,
        val phoneNumber : String,
        val address : String,
        val country : String,
        val city : String,
        val postCode : String
)
