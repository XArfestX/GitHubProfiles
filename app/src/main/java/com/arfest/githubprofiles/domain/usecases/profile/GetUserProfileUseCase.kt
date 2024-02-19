package com.arfest.githubprofiles.domain.usecases.profile

import com.arfest.githubprofiles.domain.models.UserProfile
import com.arfest.githubprofiles.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserProfileUseCase(
    private val repository: UserRepository
) {
    suspend fun execute(login: String) : UserProfile =
        repository.getUserProfile(login = login)

    suspend fun execute() : Flow<UserProfile> =
        repository.getUserProfile()
}