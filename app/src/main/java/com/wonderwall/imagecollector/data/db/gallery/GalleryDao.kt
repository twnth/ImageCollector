package com.wonderwall.imagecollector.data.db.gallery

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GalleryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(content: GalleryEntity)

    @Query("SELECT * FROM gallery_list ORDER BY regDate ASC")
    suspend fun getAll(): List<GalleryEntity>

    @Delete
    suspend fun delete(content: GalleryEntity)
}