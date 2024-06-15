package com.programardores.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Entrega(
    var id: Int = 0,
    var epi_id: Int = 0,
    var funcionario_id: Int = 0,
    var data_entrega: String = "",
    var ca: Int = 0,
    var periodo: String = ""
)