package br.com.meuscontatos.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TB_CONTATO")
data class Contato(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var nome: String = "",
    var telefone: String = "",
    @ColumnInfo(name = "is_amigo") var isAmigo: Boolean = false
)
