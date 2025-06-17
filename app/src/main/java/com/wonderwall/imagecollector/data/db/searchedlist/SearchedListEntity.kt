package com.wonderwall.imagecollector.data.db.searchedlist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cached_list")
data class SearchedListEntity (
    @PrimaryKey val keyword: String,
    val data: String,
    val cachedAt: Long
)