package com.volchok.rocketapp.app.system

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.volchok.rocketapp.app.model.BackNavigationEvent
import com.volchok.rocketapp.app.model.ForwardNavigationEvent
import com.volchok.rocketapp.app.model.Route
import com.volchok.rocketapp.app.presentation.MainViewModel
import com.volchok.rocketapp.feature.home.system.HomeScreen
import com.volchok.rocketapp.ui.theme.RocketAppTheme
import org.koin.androidx.compose.getViewModel

@Composable
fun MainScreen() {
    val viewModel = getViewModel<MainViewModel>()

    MainScreenImpl(viewModel = viewModel)
}

@Composable
fun MainScreenImpl(
    viewModel: MainViewModel,
) {
    RocketAppTheme {
        val navController = rememberNavController()

        NavigationEffect(
            navController = navController,
            viewModel = viewModel,
            onNavigationEventConsumed = viewModel::onNavigationEventConsumed
        )

        Column(
            modifier = Modifier
                //.background()
                .fillMaxSize()
        ) {
            Screens(
                navController = navController,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun Screens(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Route.Initial(),
        modifier = modifier
    ) {
        //composable(Route.Splash()) { SplashScreen() }
        composable(Route.Home()) { HomeScreen() }
    }
}

@Composable
private fun NavigationEffect(
    navController: NavHostController,
    viewModel: MainViewModel,
    onNavigationEventConsumed: () -> Unit
) {
    val state = viewModel.states.collectAsState()
    val navigationEvent = state.value.navigationEvent

    SideEffect {
        when (navigationEvent) {
            is BackNavigationEvent -> {
                navController.navigateUp()
                onNavigationEventConsumed()
            }
            is ForwardNavigationEvent -> {
                if (navController.currentDestination?.route != navigationEvent.route()) {
                    var navOptions = prepareNavOptions(navigationEvent)

                    navController.navigate(navigationEvent.route(), navOptions)
                    onNavigationEventConsumed()
                }
            }
            null -> Unit
           // else -> {}
        }
    }
}

private fun prepareNavOptions(navigationEvent: ForwardNavigationEvent): NavOptions? {
    return if (navigationEvent.clearBackStack) {
        NavOptions.Builder().also { navOptionsBuilder ->
            navigationEvent.clearBackStackRoute?.let {
                navOptionsBuilder.setPopUpTo(it(), false)
            } ?: navOptionsBuilder.setPopUpTo(0, false)
        }.build()
    } else {
        null
    }
}