package com.example.estoque.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.estoque.model.Produto

@Dao
interface ProdutoDAO {

    @Insert
    fun salvar(produto: Produto): Long

    @Update
    fun atualizar(produto: Produto): Int

    @Delete
    fun excluir(produto: Produto): Int

    @Delete
    fun excluir(listaProdutos: List<Produto>): Int

    @Query("SELECT * FROM TB_PRODUTOS ORDER BY nome")
    fun buscarTodos(): List<Produto>

    @Query("SELECT * FROM TB_PRODUTOS WHERE id = :idBusca")
    fun buscarPorIdProduto(idBusca: Long): Produto

}