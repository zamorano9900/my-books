package com.zamorano.myhouses.model.db

import com.zamorano.mybooks.model.api.House
import com.zamorano.mybooks.model.db.BaseDbRepository
import com.zamorano.mybooks.model.db.dbEntities.HouseEntity
import com.zamorano.mybooks.model.db.schema.AppDatabase


class HouseDbRepository : BaseDbRepository() {
    companion object {
        fun saveAll(lstCategories: List<House>, database: AppDatabase) {
            lstCategories.forEach { p ->
                executeQuery {
                    val exist = database.housesDao().getById(p.id)
                    if (exist.count() == 0) {
                        database.housesDao().insert(
                            houseToHouseEntity(p)
                        )
                    }
                }
            }
        }

        fun getAll(database: AppDatabase): List<House> {
            var lstCategories: List<HouseEntity>? = null
            executeQuery {
                lstCategories = database.housesDao().getAll()
            }
            return lstCategories!!.map { houseEntityToHouse(it) }
        }

        private fun houseToHouseEntity(house: House): HouseEntity {
            return HouseEntity(
                house.id,
                house.name,
                house.region,
                house.title
            )
        }

        private fun houseEntityToHouse(houseEntity: HouseEntity): House {
            return House(
                houseEntity.id,
                houseEntity.name,
                houseEntity.region,
                houseEntity.title
            )
        }
    }
}