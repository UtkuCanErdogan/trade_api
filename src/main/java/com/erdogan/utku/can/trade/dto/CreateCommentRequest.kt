package com.erdogan.utku.can.trade.dto

data class CreateCommentRequest(
        val userId : Long,
        val advertId : Long,
        val name : String,
        val surname : String,
        val message : String
)
