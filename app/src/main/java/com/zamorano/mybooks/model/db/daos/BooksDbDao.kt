package com.zamorano.mybooks.model.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.zamorano.mybooks.model.db.dbEntities.BookEntity

@Dao
abstract class BooksDbDao : BaseDao<BookEntity> {

    @Query("SELECT * from Book")
    abstract fun getAll(): List<BookEntity>

    @Query("SELECT * FROM Book WHERE isbn = :isbn")
    abstract fun getById(isbn: String): List<BookEntity>
}