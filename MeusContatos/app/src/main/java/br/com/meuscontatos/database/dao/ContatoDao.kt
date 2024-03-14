package br.com.meuscontatos.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.meuscontatos.model.Contato

@Dao
interface ContatoDao {

    @Insert
    fun salvar(contato: Contato): Long

    @Update
    fun atualizar(contato: Contato): Int

    @Delete
    fun excluir(contato: Contato): Int

    @Query("SELECT * FROM TB_CONTATO WHERE ID = :id")
    fun buscarContatoPeloId(id: Long): Contato

    @Query("SELECT * FROM TB_CONTATO ORDER BY nome ASC")
    fun listarContatos(): List<Contato>
}