package com.volchok.rocketapp.library.preferences.system

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.volchok.rocketapp.feature.favorites.model.FavoritesModel
import com.volchok.rocketapp.library.preferences.data.DataStoreResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AndroidDataStoreResource(
    private val context: Context
) : DataStoreResource {

    override fun observeItems(): Flow<List<FavoritesModel>> {
        return context.dataStore.data.map { preferences ->
            val rockets = mutableListOf<FavoritesModel>()
            val favorites = listOf<FavoritesModel>()

            favorites.forEach { item ->
                if (item.rocket_id != null) {
                    if (preferences[booleanPreferencesKey(item.rocket_id)] == true) {
                        rockets.add(item)
                    }
                }
            }
            rockets
        }
    }

    override suspend fun saveItems(favoriteRocket: List<FavoritesModel>) {
        context.dataStore.edit { preferences ->
            val favorites = listOf<FavoritesModel>()
            favorites.forEach { item ->
                if (item.rocket_id != null) {
                    preferences[booleanPreferencesKey(item.rocket_id)] =
                        favoriteRocket.contains(item)
                }
            }
        }
    }

    override suspend fun deleteItems() {
        context.dataStore.edit { preferences ->
            val favorites = listOf<FavoritesModel>()
            favorites.forEach { item ->
                if (item.rocket_id != null) {
                    // TODO
//                if (preferences.contains(booleanPreferencesKey(item.rocket_id)))
                    preferences.remove(booleanPreferencesKey(item.rocket_id))
                }
            }
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "RocketAppDataStore")
    }
}