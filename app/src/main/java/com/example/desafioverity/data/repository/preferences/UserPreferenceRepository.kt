package com.example.desafioverity.data.repository.preferences

import kotlinx.coroutines.flow.Flow

interface UserPreferenceRepository {
    suspend fun saveRemaining(remaining: String)
    suspend fun saveTimeNewRequest(newTime:String)
    val getRemaining: Flow<String>
    val getTimeForNewRequest:Flow<String>
}