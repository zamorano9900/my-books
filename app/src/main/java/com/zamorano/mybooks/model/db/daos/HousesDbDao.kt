package com.zamorano.mybooks.model.db.daos

import androidx.room.Dao
import androidx.room.Query
import com.zamorano.mybooks.model.db.dbEntities.HouseEntity

@Dao
abstract class HousesDbDao : BaseDao<HouseEntity> {

    @Query("SELECT * from House")
    abstract fun getAll(): List<HouseEntity>

    @Query("SELECT * FROM House WHERE id = :id")
    abstract fun getById(id: String): List<HouseEntity>
}