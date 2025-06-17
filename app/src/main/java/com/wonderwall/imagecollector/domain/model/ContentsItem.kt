package com.wonderwall.imagecollector.domain.model

data class ContentsItem(
    val idx: Int = 0,
    val thumbnailUrl: String? = "",
    val dateTime: String = "",
    val isFavorite: Boolean = false,
    val type: ContentsType = ContentsType.IMAGE
)