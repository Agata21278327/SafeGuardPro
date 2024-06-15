package com.programardores.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Epi(
    var id: Int = 0,
    var ca: Int = 0,
    var nome: String = "",
    var descricao: String = "",
    var validade: String = ""
)
