package com.wonderwall.imagecollector.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wonderwall.imagecollector.data.db.gallery.GalleryDao
import com.wonderwall.imagecollector.data.db.gallery.GalleryEntity
import com.wonderwall.imagecollector.data.db.searchedlist.SearchedListDao
import com.wonderwall.imagecollector.data.db.searchedlist.SearchedListEntity

@Database(
    entities = [SearchedListEntity::class, GalleryEntity::class],
    version = 1,
    exportSchema = false
)
abstract class ICDatabase : RoomDatabase() {
    abstract fun searchedListDao(): SearchedListDao
    abstract fun galleryDao(): GalleryDao
}
