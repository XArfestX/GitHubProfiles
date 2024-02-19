package com.arfest.githubprofiles.data.storage.api

import com.arfest.githubprofiles.data.storage.model.ProfileEntity
import kotlinx.coroutines.flow.Flow

interface ProfileStorage {

    fun add(profile: ProfileEntity)

    suspend fun observe(login: String): Flow<ProfileEntity>
}