package com.rodionovmax.mygithubapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class, RepoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repoDao: RepoDao

    companion object {
        private const val DB_NAME = "github.db"

        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance() = INSTANCE ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?): LocalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context!!, LocalDatabase::class.java, DB_NAME)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}