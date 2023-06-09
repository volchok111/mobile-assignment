package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ObserveRocketsUseCase(
    private val repository: RemoteRepository
) : SuspendUseCase<Unit, Flow<Data<List<RocketItem>>>> {
    override suspend fun invoke(input: Unit): Flow<Data<List<RocketItem>>> = flow {
        emit(repository.getRockets())
    }
}