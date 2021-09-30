package com.erdogan.utku.can.trade.dto

data class UpdateUserDetailsRequest(
        val phoneNumber : String,
        val address : String,
        val country : String,
        val city : String,
        val postCode : String
)