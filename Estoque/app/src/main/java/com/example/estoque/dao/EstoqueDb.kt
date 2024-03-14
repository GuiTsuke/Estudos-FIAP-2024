package com.example.estoque.dao

import android.content.Context
import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.example.estoque.model.Produto

@Database(entities = [Produto::class], version = 1)
abstract class EstoqueDb : RoomDatabase() {

    abstract fun produtoDAO(): ProdutoDAO

    companion object {
        private lateinit var instancia: EstoqueDb

        fun getDataBase(context: Context): EstoqueDb {
            if (!(::instancia.isInitialized)) {
                instancia = Room
                    .databaseBuilder(
                        context = context,
                        EstoqueDb::class.java,
                        "estoque_db"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instancia
        }
    }
}