package com.programardores.safeguardpro.service.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "funcionario")
data class Funcionario(
    @PrimaryKey
    var id: Int = 0,
    var nome: String = "",
    var cpf: String = ""
)