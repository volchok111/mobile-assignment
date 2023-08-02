package com.volchok.rocketapp.feature.home.system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.volchok.rocketapp.R
import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.feature.home.presentation.HomeViewModel
import com.volchok.rocketapp.library.ui.*
import com.volchok.rocketapp.library.ui.RocketColors.chrome900
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeS
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeXS
import org.koin.androidx.compose.getViewModel

@Composable
fun HomeScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    val state = viewModel.states.collectAsState()

    LaunchedEffect(viewModel) {
        viewModel.loadData()
    }

    HomeScreenImpl(
        state = state.value,
        viewModel::onItem
    )
}

@Composable
private fun HomeScreenImpl(
    state: HomeViewModel.State,
    onItem: (String) -> Unit = {}
) {
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
                text = stringResource(id = R.string.home_screen_title),
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
                    .padding(start = sizeS, end = sizeS)
            ) {
                LazyColumn {
                    itemsIndexed(state.rockets) { index, item ->
                        if (index != 0 && index != 4) {
                            Divider(color = RocketColors.chrome100, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(sizeXS))
                        if (item != null) {
                            RocketListItem(
                                item = item,
                                onClick = {
                                    if (it != null) {
                                        onItem(it)
                                    }
                                }
                            )
                        }
                        Spacer(modifier = Modifier.height(sizeXS))
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.height(RocketDimensions.sizeL))
            RocketText(
                text = "Favorites",
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
                    .padding(start = sizeS, end = sizeS)
            ) {
                LazyColumn {
                    itemsIndexed(state.favorites) { index, item ->
                        if (index != 0 && index != 4) {
                            Divider(color = RocketColors.chrome100, thickness = 1.dp)
                        }
                        Spacer(modifier = Modifier.height(sizeXS))
                        FavoriteListItem(
                            item = item,
                            onClick = {
                                if (it != null) {
                                    onItem(it)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.height(sizeXS))
                    }
                }
            }
        }
    }

    if (state.loading) {
        RocketLoadingDialog(title = "")
    }
}

@Composable
private fun RocketListItem(
    item: HomeViewModel.State.RocketItem,
    onClick: (String?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(item.rocket_id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RocketIcon(
            icon = R.drawable.rocket,
            contentDescription = null,
            tint = RocketColors.pink,
            modifier = Modifier
                .size(RocketDimensions.sizeL)
                .padding(end = sizeXS)
                .align(Alignment.CenterVertically)
        )

        Column(modifier = Modifier.weight(1f)) {
            item.rocket_name?.let {
                RocketText(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    color = chrome900,
                    fontWeight = FontWeight.Bold
                )
            }
            RocketText(
                text = "${stringResource(id = R.string.home_screen_first_flight)} ${item.first_flight}",
                style = MaterialTheme.typography.subtitle2,
                color = RocketColors.chrome300
            )
        }
        RocketIcon(
            icon = R.drawable.icon_navigate_next,
            contentDescription = null,
            tint = RocketColors.chrome300,
            modifier = Modifier
                .size(RocketDimensions.sizeXL)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
private fun FavoriteListItem(
    item: FavoritesModel,
    onClick: (String?) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onClick(item.rocket_id) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RocketIcon(
            icon = R.drawable.rocket,
            contentDescription = null,
            tint = RocketColors.pink,
            modifier = Modifier
                .size(RocketDimensions.sizeL)
                .padding(end = sizeXS)
                .align(Alignment.CenterVertically)
        )

        Column(modifier = Modifier.weight(1f)) {
            item.rocket_name?.let {
                RocketText(
                    text = it,
                    style = MaterialTheme.typography.h6,
                    color = chrome900,
                    fontWeight = FontWeight.Bold
                )
            }
            RocketText(
                text = "${stringResource(id = R.string.home_screen_first_flight)} ${item.first_flight}",
                style = MaterialTheme.typography.subtitle2,
                color = RocketColors.chrome300
            )
        }
        RocketIcon(
            icon = R.drawable.icon_navigate_next,
            contentDescription = null,
            tint = RocketColors.chrome300,
            modifier = Modifier
                .size(RocketDimensions.sizeXL)
                .align(Alignment.CenterVertically)
        )
    }
}