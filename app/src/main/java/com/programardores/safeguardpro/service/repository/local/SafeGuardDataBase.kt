package com.programardores.safeguardpro.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.programardores.safeguardpro.service.model.Funcionario

@Database(entities = [Funcionario::class], version = 1)
abstract class SafeGuardDataBase: RoomDatabase() {

    abstract fun funcionarioDAO(): FuncionarioDAO

    companion object{
        @Volatile
        private lateinit var INSTANCE: SafeGuardDataBase

        fun getDataBase(context: Context): SafeGuardDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(this) {
                    INSTANCE =
                        Room.databaseBuilder(context, SafeGuardDataBase::class.java, "safeguard_datbase")
                            .build()
                }

            }
            return INSTANCE
        }
    }
}