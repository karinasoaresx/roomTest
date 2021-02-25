package com.example.roomtest.dao

import androidx.room.*
import com.example.roomtest.model.Contato

//para o room saber que essa interface eh pra ele controlar
@Dao
interface ContatoDao {

    //inserir no banco
    @Insert
    fun salvar(contato: Contato)

    //atualizar
    @Update
    fun atualizar(contato: Contato)

    //listar em ordem crescente
    @Query("SELECT * FROM Contato ORDER BY nome ASC")
    fun listarTodos() : List<Contato>

    //listar contato pelo id
    @Query("SELECT * FROM Contato WHERE id = :id")
    fun listarPorId(id: Int) : Contato

    //apagar contato
    @Delete
    fun excluir(contato: Contato)
}