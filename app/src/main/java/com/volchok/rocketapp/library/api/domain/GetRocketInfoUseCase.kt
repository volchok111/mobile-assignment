package com.volchok.rocketapp.library.api.domain

import com.volchok.rocketapp.library.api.model.details.RocketDetailsModel
import com.volchok.rocketapp.library.data.model.Data
import com.volchok.rocketapp.library.rockets.domain.GetSelectedRocketIdUseCase
import com.volchok.rocketapp.library.use_case.domain.SuspendUseCase
import com.volchok.rocketapp.library.use_case.domain.invoke
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetRocketInfoUseCase(
    private val repository: RemoteRepository,
    private val getSelectedRocketIdUseCase: GetSelectedRocketIdUseCase
) : SuspendUseCase<Unit, Flow<Data<RocketDetailsModel>>> {
    override suspend fun invoke(input: Unit): Flow<Data<RocketDetailsModel>> = flow {
        emit(repository.getRocketInfo(getSelectedRocketIdUseCase()))
    }
}