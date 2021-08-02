package com.zamorano.mybooks.model.db.dbEntities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "House")
data class HouseEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "region") var region: String,
    @ColumnInfo(name = "title") var title: String)


