package com.zamorano.mybooks.model.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.zamorano.mybooks.model.db.dbEntities.CharEntity

@Dao
abstract class CharsDbDao : BaseDao<CharEntity> {

    @Query("SELECT * from Char")
    abstract fun getAll(): List<CharEntity>

    @Query("SELECT * FROM Char WHERE id = :id")
    abstract fun getById(id: String): List<CharEntity>
}