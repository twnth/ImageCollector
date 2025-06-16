package com.wonderwall.imagecollector.data

import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("is_end")
    val isEnd: Boolean = false,
    @SerializedName("pageable_count")
    val pageableCount: Int = 0,
    @SerializedName("total_count")
    val totalCount: Int = 0
)