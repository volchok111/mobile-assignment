package com.volchok.rocketapp.library.preferences.system

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.preferences.data.DataStoreResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidDataStoreResource(
    private val context: Context
) : DataStoreResource {

    override fun observeRockets(favoriteRocket: List<RocketItem>): Flow<List<RocketItem>> {
        return context.dataStore.data.map { preferences ->
            val rockets = mutableListOf<RocketItem>()

            favoriteRocket.forEach { item ->
                if (item.rocket_id != null) {
                    if (preferences[booleanPreferencesKey(item.rocket_id)] == true) {
                        rockets.add(item)
                    }
                }
            }
            rockets
        }
    }

    override suspend fun setRockets(favoriteRocket: List<RocketItem>) {
        context.dataStore.edit { preferences ->
            favoriteRocket.forEach { item ->
                if (item.rocket_id != null) {
                    preferences[booleanPreferencesKey(item.rocket_id)] =
                        favoriteRocket.contains(item)
                }
            }
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "RocketAppDataStore")
    }
}