package com.zamorano.mybooks.model.db.dbEntities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "type") var type: String,
    @ColumnInfo(name = "category_name") var category_name: String
)

