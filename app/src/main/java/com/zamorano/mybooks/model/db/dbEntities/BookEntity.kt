package com.zamorano.mybooks.model.db.dbEntities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zamorano.mybooks.helpers.Converters

@Entity(tableName = "Book")
data class BookEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "isbn") var isbn: String,
    @ColumnInfo(name = "name") var name: String,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "authors") var authors: MutableList<String> = arrayListOf(),
    @ColumnInfo(name = "numberOfPages") var numberOfPages: Int,
    @ColumnInfo(name = "publisher") var publisher: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "mediaType") var mediaType: String,
    @ColumnInfo(name = "released") var released: String
)



