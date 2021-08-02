package com.zamorano.mybooks.model.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.zamorano.mybooks.model.db.dbEntities.CategoryEntity

@Dao
abstract class CategoriesDbDao : BaseDao<CategoryEntity> {

    @Query("SELECT * from Category")
    abstract fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM Category WHERE type = :type")
    abstract fun getById(type: String): List<CategoryEntity>
}