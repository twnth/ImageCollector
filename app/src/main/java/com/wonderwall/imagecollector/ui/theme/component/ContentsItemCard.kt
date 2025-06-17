package com.wonderwall.imagecollector.ui.theme.component

import android.os.Build
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.wonderwall.imagecollector.R
import com.wonderwall.imagecollector.core.parseToLocalDateTimeWithOffset
import com.wonderwall.imagecollector.domain.model.ContentsItem

@Composable
fun ContentsItemCard(item: ContentsItem, onClick: (ContentsItem) -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clickable { onClick(item) },
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = item.thumbnailUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(8.dp)
        ) {
            val icon = if (item.isFavorite) {
                painterResource(R.drawable.ic_selected)
            } else {
                painterResource(R.drawable.ic_unselected)
            }
            Icon(
                painter = icon,
                contentDescription = "Selected Icon",
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.TopEnd),
                tint = Color.Unspecified
            )
            val dateText = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                item.dateTime.parseToLocalDateTimeWithOffset()
            } else {
                item.dateTime
            }

            Text(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = dateText
            )
        }
    }
}