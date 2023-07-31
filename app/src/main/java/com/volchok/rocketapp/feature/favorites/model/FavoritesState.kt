package com.volchok.rocketapp.feature.favorites.model

import com.volchok.rocketapp.library.api.model.home.RocketItem

data class FavoritesState(
    val allRockets: List<RocketItem> = emptyList(),
    val selectedRockets: Set<RocketItem> = emptySet(),
    val initialSelectedRockets: Set<RocketItem> = emptySet()
) {
    val hasChanges: Boolean
        get() = selectedRockets != initialSelectedRockets
}
