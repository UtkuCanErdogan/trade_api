package com.erdogan.utku.can.trade.dto

import com.erdogan.utku.can.trade.model.Category
import com.erdogan.utku.can.trade.model.Mark
import java.math.BigDecimal

data class AdvertDto(
        val productName : String?,
        val category: Category,
        val mark : Mark?,
        val description : String?,
        val price : BigDecimal?
)