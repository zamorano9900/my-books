package com.zamorano.mybooks.model.db

import com.zamorano.mybooks.model.api.Char
import com.zamorano.mybooks.model.db.dbEntities.CharEntity
import com.zamorano.mybooks.model.db.schema.AppDatabase

class CharDbRepository : BaseDbRepository() {
    companion object {
        fun saveAll(lstCategories: List<Char>, database: AppDatabase) {
            lstCategories.forEach { p ->
                executeQuery {
                    val exist = database.charsDao().getById(p.id)
                    if (exist.count() == 0) {
                        database.charsDao().insert(
                            charToCharEntity(p)
                        )
                    }
                }
            }
        }

        fun getAll(database: AppDatabase): List<Char> {
            var lstCategories: List<CharEntity>? = null
            executeQuery {
                lstCategories = database.charsDao().getAll()
            }
            return lstCategories!!.map { charEntityToChar(it) }
        }

        private fun charToCharEntity(char: Char): CharEntity {
            return CharEntity(
                char.id,
                char.name,
                char.gender,
                char.culture,
                char.born,
                char.died,
                char.titles,
                char.aliases,
                char.father,
                char.mother,
                char.spouse,
                char.allegiances,
                char.playedBy
            )
        }

        private fun charEntityToChar(charEntity: CharEntity): Char {
            return Char(
                charEntity.id,
                charEntity.name,
                charEntity.gender,
                charEntity.culture,
                charEntity.born,
                charEntity.died,
                charEntity.titles,
                charEntity.aliases,
                charEntity.father,
                charEntity.mother,
                charEntity.spouse,
                charEntity.allegiances,
                charEntity.playedBy
            )
        }
    }
}