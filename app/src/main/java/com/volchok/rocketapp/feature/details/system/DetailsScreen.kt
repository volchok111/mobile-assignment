package com.volchok.rocketapp.feature.details.system

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.volchok.rocketapp.feature.details.presentation.DetailsViewModel
import com.volchok.rocketapp.library.ui.*
import com.volchok.rocketapp.library.ui.RocketColors.pink
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeM
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeS
import com.volchok.rocketapp.library.ui.RocketDimensions.sizeXS
import org.koin.androidx.compose.getViewModel

@Composable
fun DetailsScreen() {
    val viewModel = getViewModel<DetailsViewModel>()
    val state = viewModel.states.collectAsState()

    DetailsScreenImpl(
        state = state.value
    )
}

@Composable
private fun DetailsScreenImpl(
    state: DetailsViewModel.State
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(sizeS)
            .verticalScroll(rememberScrollState())
    ) {

        RocketText(
            text = stringResource(id = com.volchok.rocketapp.R.string.details_screen_overview),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(sizeS))

        RocketText(
            text = state.rocket?.description.orEmpty(),
            style = MaterialTheme.typography.body1
        )
        Spacer(modifier = Modifier.height(sizeS))

        RocketText(
            text = stringResource(id = com.volchok.rocketapp.R.string.details_screen_parameters),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(sizeS))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ParametersItem(
                title = state.rocket?.height?.meters.toString() + "m",
                subtitle = stringResource(id = com.volchok.rocketapp.R.string.details_screen_parameters_height)
            )
            ParametersItem(
                title = state.rocket?.diameter?.meters.toString() + "m",
                subtitle = stringResource(id = com.volchok.rocketapp.R.string.details_screen_parameters_diameter)
            )
            ParametersItem(
                title = (state.rocket?.mass?.kg?.div(1000)).toString() + "t",
                subtitle = stringResource(id = com.volchok.rocketapp.R.string.details_screen_parameters_mass)
            )
        }

        Spacer(modifier = Modifier.height(sizeM))

        RocketInfoCard(
            title = stringResource(id = com.volchok.rocketapp.R.string.details_screen_first_stage),
            reusable = state.rocket?.first_stage?.reusable.let {
                if (it == true) stringResource(id = com.volchok.rocketapp.R.string.details_screen_reusable) else stringResource(
                    id = com.volchok.rocketapp.R.string.details_screen_not_reusable
                )
            },
            enginesCount = state.rocket?.first_stage?.engines.toString(),
            fuel = state.rocket?.first_stage?.fuel_amount_tons.toString(),
            seconds = state.rocket?.first_stage?.burn_time_sec.toString()
        )
        Spacer(modifier = Modifier.height(sizeS))

        RocketInfoCard(
            title = stringResource(id = com.volchok.rocketapp.R.string.details_screen_second_stage),
            reusable = state.rocket?.second_stage?.reusable.let { if (it == true) "reusable" else "not reusable" },
            enginesCount = state.rocket?.second_stage?.engines.toString(),
            fuel = state.rocket?.second_stage?.fuel_amount_tons.toString(),
            seconds = state.rocket?.second_stage?.burn_time_sec.toString()
        )
        Spacer(modifier = Modifier.height(sizeM))

        RocketText(
            text = stringResource(id = com.volchok.rocketapp.R.string.details_screen_photos),
            style = MaterialTheme.typography.h6,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(sizeS))
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(state.rocket?.flickr_images?.get(0)),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(sizeS))

        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(state.rocket?.flickr_images?.get(1)),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(RocketDimensions.sizeL))
    }

    if (state.loading) {
        RocketLoadingDialog(title = "")
    }
}

@Composable
private fun ParametersItem(
    title: String,
    subtitle: String
) {
    Card(
        modifier = Modifier
            .size(110.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(20.dp),
        backgroundColor = pink
    ) {
        Column(
            modifier = Modifier
                .padding(sizeS),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            RocketText(
                text = title,
                style = MaterialTheme.typography.h5,
                color = RocketColors.white,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                maxLines = 1,

                )
            Spacer(modifier = Modifier.height(sizeXS))

            RocketText(
                text = subtitle,
                style = MaterialTheme.typography.body1,
                color = RocketColors.white
            )
        }
    }
}

@Composable
private fun RocketInfoCard(
    title: String,
    enginesCount: String,
    fuel: String,
    seconds: String,
    reusable: String
) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(20.dp),
        backgroundColor = RocketColors.chrome50
    ) {
        Column(
            modifier = Modifier
                .padding(sizeS)
        ) {

            RocketText(text = title, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = com.volchok.rocketapp.R.drawable.reusable,
                value = reusable
            )
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = com.volchok.rocketapp.R.drawable.engine,
                value = "$enginesCount ${stringResource(id = com.volchok.rocketapp.R.string.details_screen_engines)}"
            )
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = com.volchok.rocketapp.R.drawable.fuel,
                value = "$fuel ${stringResource(id = com.volchok.rocketapp.R.string.details_screen_tons)}"
            )
            Spacer(modifier = Modifier.height(sizeS))

            RocketInfoItem(
                icon = com.volchok.rocketapp.R.drawable.burn,
                value = "$seconds ${stringResource(id = com.volchok.rocketapp.R.string.details_screen_seconds)}"
            )
        }
    }
}

@Composable
private fun RocketInfoItem(
    icon: Int,
    value: String
) {
    Row(horizontalArrangement = Arrangement.Center) {
        RocketIcon(
            icon = icon,
            contentDescription = null,
            modifier = Modifier
                .padding(end = sizeXS)
                .size(sizeM),
            tint = pink
        )
        RocketText(text = value, style = MaterialTheme.typography.body1)
    }
}
