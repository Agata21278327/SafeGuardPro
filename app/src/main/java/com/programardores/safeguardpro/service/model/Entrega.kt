package com.programardores.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "entrega")
data class Entrega(
    @PrimaryKey
    var id: Int = 0,
    var data_entrega: String = "",
    var ca: Int = 0,
    var periodo: String = ""
)