package com.kriaactividade.xitiqueapp.data.repository.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.desafioverity.data.repository.preferences.UserPreferenceRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserPreferencesRepositoryImp @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : UserPreferenceRepository {

    companion object {
        private val USER_TOKEN_KEY = stringPreferencesKey("token_key")
        private val DATE_REQUEST = stringPreferencesKey("date_request")
    }

    override suspend fun saveRemaining(remaining: String) {
        dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = remaining
        }
    }

    override suspend fun saveTimeNewRequest(newTime: String) {
        dataStore.edit { preferences ->
            preferences[DATE_REQUEST] = newTime
        }
    }

    override val getRemaining: Flow<String> = dataStore.data.map { preferences ->
        preferences[USER_TOKEN_KEY] ?: ""
    }
    override val getTimeForNewRequest: Flow<String> = dataStore.data.map { preferences->
        preferences[DATE_REQUEST]?: ""
    }
}