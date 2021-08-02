package com.zamorano.mybooks.model.db.schema

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zamorano.mybooks.helpers.Converters
import com.zamorano.mybooks.model.db.dbEntities.BookEntity
import com.zamorano.mybooks.model.db.dbEntities.CategoryEntity
import com.zamorano.mybooks.model.db.dbEntities.CharEntity
import com.zamorano.mybooks.model.db.dbEntities.HouseEntity

@Database(entities = [BookEntity::class, CategoryEntity::class, HouseEntity::class, CharEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    companion object {

        const val DATABASE_NAME = "myBooksApp.db"
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE
        }

        fun getMemoryInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.inMemoryDatabaseBuilder(context.applicationContext,
                            AppDatabase::class.java)
                            .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}