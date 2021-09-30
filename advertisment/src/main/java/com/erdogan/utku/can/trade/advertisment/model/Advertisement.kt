package com.erdogan.utku.can.trade.advertisment.model

import org.springframework.data.elasticsearch.annotations.Document
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Document(indexName = "advertisment")
data class Advertisement(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id :Long?,
        val title : String,
)