package com.example.roomtest.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

//essa classe vai ser uma entidade no banco de dados
@Entity
data class Contato(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    //definir como o nome vai ficar salvo no banco de dados
    @ColumnInfo(name = "nome")
    var nomeContato: String,

    @ColumnInfo(name = "telefone")
    var telefoneContato: String? //se quiser deixar como "null", tem q colocar "?" no final
)