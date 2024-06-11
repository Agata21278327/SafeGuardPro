package com.programardores.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "epi")
data class Epi(
    @PrimaryKey
    var id: Int = 0,
    var nome: String = "0",
    var descricao: String = "",
    var validade: String = ""
)
