package com.volchok.rocketapp.library.preferences.system

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.volchok.rocketapp.library.api.model.home.RocketItem
import com.volchok.rocketapp.library.preferences.data.DataStoreResource
import kotlinx.coroutines.flow.first

class AndroidDataStoreResource(
    private val context: Context
) : DataStoreResource {

    override suspend fun saveItems(favoriteRocket: List<RocketItem>) {
        val rocketDataStoreKey = stringPreferencesKey(KEY_FAVORITE_ROCKET)

        context.dataStore.edit { preferences ->
            preferences[rocketDataStoreKey] = Gson().toJson(favoriteRocket)
        }
    }

    override suspend fun getItems(): List<RocketItem>? {
        val rocketDataStoreKey = stringPreferencesKey(KEY_FAVORITE_ROCKET)

        val preferences = context.dataStore.data.first()

        val listType = object : TypeToken<List<RocketItem>?>() {}.type
        return Gson().fromJson(preferences[rocketDataStoreKey], listType)
    }

    override suspend fun getItemById(rocketId: String): RocketItem? {
        val rocketsList = getItems()

        if (!rocketsList.isNullOrEmpty()) {
            rocketsList.forEach {
                if (it.rocket_id == rocketId) {
                    return it
                }
            }
        }
        return null
    }

    override suspend fun updateFavoriteByItemId(rocketId: String) {
        val rocketList = getItems()

        if (!rocketList.isNullOrEmpty()) {
            rocketList.forEach {
                if (it.rocket_id == rocketId) {
                    it.isFavorite = !it.isFavorite
                    deleteItems()
                    saveItems(rocketList)
                }
            }
        }
    }


    override suspend fun deleteItems() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "RocketAppDataStore")
        private const val KEY_FAVORITE_ROCKET = "key.favorite.rocket"
    }
}