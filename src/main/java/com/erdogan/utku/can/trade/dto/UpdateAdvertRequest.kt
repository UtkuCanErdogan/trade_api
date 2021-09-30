package com.erdogan.utku.can.trade.dto

import java.math.BigDecimal

data class UpdateAdvertRequest(
        val productName : String,
        val description : String?,
        val price : BigDecimal?
)