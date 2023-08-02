package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.feature.favorites.domain.FavoriteRocketRepository
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class ObserveRocketsUseCase(
    private val repository: RemoteRepository,
    private val favoriteRocketRepository: FavoriteRocketRepository
) : SuspendUseCase<Unit, Flow<Data<List<RocketItem>>>> {
    override suspend fun invoke(input: Unit): Flow<Data<List<RocketItem>>> {
        val result: List<RocketItem> = favoriteRocketRepository.getFavoriteRockets().first()
        return if (result.isEmpty()) {
            flowOf(repository.getRockets())
        } else {
            val getDownloadedItemsResult = favoriteRocketRepository.getFavoriteRockets().first()
            flowOf(Data.Success(getDownloadedItemsResult))
        }
    }
}