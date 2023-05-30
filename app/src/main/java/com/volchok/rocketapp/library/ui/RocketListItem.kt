package com.volchok.rocketapp.library.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.volchok.rocketapp.R
import com.volchok.rocketapp.library.ui.RocketColors.chrome300
import com.volchok.rocketapp.library.ui.RocketColors.chrome900

@Composable
fun RocketListItem(
    title: String,
    subtitle: String,
    onItemClick: () -> Unit
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .clickable { onItemClick() }
    ) {
        Column(modifier = Modifier.weight(1f)) {
            RocketText(
                text = title,
                style = MaterialTheme.typography.h4,
                color = chrome900,
                fontWeight = FontWeight.Bold
            )
            RocketText(
                text = subtitle,
                style = MaterialTheme.typography.subtitle2,
                color = chrome300
            )
        }
        RocketIcon(
            icon = R.drawable.icon_navigate_next,
            contentDescription = null,
            tint = RocketColors.chrome600,
            modifier = Modifier.size(RocketDimensions.sizeXXL)
        )
    }
}