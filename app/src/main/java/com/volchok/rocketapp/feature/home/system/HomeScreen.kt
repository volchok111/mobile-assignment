package com.volchok.rocketapp.feature.home.system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.volchok.rocketapp.feature.home.presentation.HomeViewModel
import com.volchok.rocketapp.library.ui.RocketColors
import com.volchok.rocketapp.library.ui.RocketColors.chrome900
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeS
import com.volchok.rocketapp.library.ui.RocketListItem
import com.volchok.rocketapp.library.ui.RocketText
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    val state = viewModel.states.collectAsState()

    HomeScreenImpl()
}

@Composable
private fun HomeScreenImpl() {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(sizeS)
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxWidth()
        ) {
            RocketText(
                text = stringResource(id = com.volchok.rocketapp.R.string.home_screen_title),
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Bold,
                color = chrome900
            )
        }
        Spacer(modifier = Modifier.height(sizeS))
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(RocketColors.white)
                    .padding(start = sizeS, end = sizeS, top = sizeS)
            ) {
                //TODO Add dividers
                LazyColumn {
                    items(4) { item ->
                        RocketListItem(
                            title = "Falcon 1",
                            subtitle = "First flight: 24.3.2006",
                            onItemClick = {})
                        Spacer(modifier = Modifier.height(sizeS))
                    }
                }
                // Divider(color = RocketColors.chrome300, thickness = 1.dp)
            }
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenImpl()
}