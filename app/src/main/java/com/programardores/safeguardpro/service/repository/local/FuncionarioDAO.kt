package com.programardores.safeguardpro.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.programardores.safeguardpro.service.model.Funcionario

@Dao
interface FuncionarioDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(funcionario: Funcionario)
    @Query("SELECT * FROM funcionario WHERE id = :id")
    suspend fun getFuncionario(id: Int): Funcionario

    @Query("SELECT * FROM funcionario")
    suspend fun getAll(): List<Funcionario>
    @Update
    suspend fun update(funcionario: Funcionario)
    @Query("DELETE FROM funcionario WHERE id = :id")
    suspend fun delete(id: Int)
}