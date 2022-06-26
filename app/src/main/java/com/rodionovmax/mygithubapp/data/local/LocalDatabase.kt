package com.rodionovmax.mygithubapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntity::class, RepoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
}