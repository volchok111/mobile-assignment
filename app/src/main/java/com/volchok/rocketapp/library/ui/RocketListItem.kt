package com.volchok.rocketapp.library.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.volchok.rocketapp.R
import com.volchok.rocketapp.library.ui.RocketColors.chrome300
import com.volchok.rocketapp.library.ui.RocketColors.chrome900
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeXS

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
        RocketIcon(
            icon = R.drawable.rocket,
            contentDescription = null,
            tint = RocketColors.pink,
            modifier = Modifier
                .size(RocketDimensions.sizeL)
                .padding(end = sizeXS)
                .align(CenterVertically)
        )

        Column(modifier = Modifier.weight(1f)) {
            RocketText(
                text = title,
                style = MaterialTheme.typography.h6,
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
            tint = chrome300,
            modifier = Modifier
                .size(RocketDimensions.sizeXL)
                .align(CenterVertically)
        )
    }
}