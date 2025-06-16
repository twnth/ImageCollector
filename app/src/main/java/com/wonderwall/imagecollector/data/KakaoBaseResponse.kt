package com.wonderwall.imagecollector.data

import com.google.gson.annotations.SerializedName

data class KakaoBaseResponse (
    @SerializedName("meta")
    val meta: Meta = Meta(),

    @SerializedName("documents")
    val documents: List<Document> = listOf(),
)