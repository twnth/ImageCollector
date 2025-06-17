package com.wonderwall.imagecollector.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchedListDao {
    @Query("SELECT * FROM cached_list WHERE keyword = :keyword")
    suspend fun getCache(keyword: String): SearchedListEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(entity: SearchedListEntity)

    @Query("DELETE FROM cached_list WHERE keyword = :keyword")
    suspend fun deleteCache(keyword: String)
}