package com.example.estoque.repository

import android.content.Context
import com.example.estoque.dao.EstoqueDb
import com.example.estoque.model.Produto

class ProdutoRepository(context: Context) {

    private val db = EstoqueDb.getDataBase(context).produtoDAO()

    fun salvar(produto: Produto) = db.salvar(produto)

    fun atualizar(produto: Produto) = db.atualizar(produto)

    fun deletar(produto: Produto) = db.excluir(produto)

    fun deletarLista(listaProduto: List<Produto>) = db.excluir(listaProduto)

    fun buscarProdutoPorId(idBusca: Long) = db.buscarPorIdProduto(idBusca)

    fun buscarTodos() = db.buscarTodos()

}