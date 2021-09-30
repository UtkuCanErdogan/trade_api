package com.erdogan.utku.can.trade.advertisment.model

import java.time.LocalDateTime

data class BaseEntity(
        val createdDate : LocalDateTime? = null,
        val updatedDate : LocalDateTime? = null
)