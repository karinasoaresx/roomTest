package com.example.roomtest.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomtest.model.Contato

@Database(entities = [Contato::class], version = 1)
//não pode ser instanciado
abstract class AppDataBase : RoomDatabase(){
    abstract fun ContatoDao(): ContatoDao
}