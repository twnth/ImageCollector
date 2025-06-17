package com.wonderwall.imagecollector.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [SearchedListEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ICDatabase : RoomDatabase() {
    abstract fun searchedListDao(): SearchedListDao
}
