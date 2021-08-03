package com.zamorano.mybooks.model.db.dbEntities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.zamorano.mybooks.helpers.Converters

@Entity(tableName = "Char")
data class CharEntity(
    @PrimaryKey @NonNull @ColumnInfo(name = "id") var id: String,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "gender") var gender: String,
    @ColumnInfo(name = "culture") var culture: String,
    @ColumnInfo(name = "born") var born: String,
    @ColumnInfo(name = "died") var died: String,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "titles") var titles: MutableList<String> = arrayListOf(),
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "aliases") var aliases: MutableList<String> = arrayListOf(),
    @ColumnInfo(name = "father") var father: String,
    @ColumnInfo(name = "mother") var mother: String,
    @ColumnInfo(name = "spouse") var spouse: String,
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "allegiances") var allegiances: MutableList<String> = arrayListOf(),
    @TypeConverters(Converters::class)
    @ColumnInfo(name = "playedBy") var playedBy: MutableList<String> = arrayListOf()
)


