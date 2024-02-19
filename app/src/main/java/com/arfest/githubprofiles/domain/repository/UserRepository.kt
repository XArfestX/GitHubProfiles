package com.arfest.githubprofiles.domain.repository

import com.arfest.githubprofiles.domain.models.User
import com.arfest.githubprofiles.domain.models.UserProfile
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun getUsers() : List<User>

    suspend fun getUserProfile() : Flow<UserProfile>

    suspend fun getUserProfile(login: String) : UserProfile

    suspend fun getUserFollowers(login: String) : List<User>
}