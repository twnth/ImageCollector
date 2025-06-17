package com.wonderwall.imagecollector.core

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun String.parseToLocalDateTimeWithOffset(): String {
    val odt = OffsetDateTime.parse(this) // ISO 8601 형식 파싱
    val localDateTime = odt.atZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime()
    return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}