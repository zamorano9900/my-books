package com.zamorano.mybooks.model.db

import com.zamorano.mybooks.model.api.Category
import com.zamorano.mybooks.model.db.dbEntities.CategoryEntity
import com.zamorano.mybooks.model.db.schema.AppDatabase

class CategoryDbRepository : BaseDbRepository() {
    companion object {

        fun saveAll(lstCategories: List<Category>, database: AppDatabase) {
            lstCategories.forEach { p ->
                executeQuery {
                    val exist = database.categoriesDao().getById(p.type)
                    if (exist.count() == 0) {
                        database.categoriesDao().insert(categoryToCategoryEntity(p))
                    }
                }
            }
        }

        fun getAll(database: AppDatabase): List<Category> {
            var lstCategories: List<CategoryEntity>? = null
            executeQuery {
                lstCategories = database.categoriesDao().getAll()
            }
            return lstCategories!!.map { categoryEntityToCategory(it) }
        }
        
        private fun categoryToCategoryEntity(category: Category): CategoryEntity {
            return CategoryEntity(category.type, category.category_name)
        }

        private fun categoryEntityToCategory(categoryEntity: CategoryEntity): Category {
            return Category(categoryEntity.type, categoryEntity.category_name)
        }
    }
}