package com.arfest.githubprofiles.domain.usecases.profile

import com.arfest.githubprofiles.domain.models.User
import com.arfest.githubprofiles.domain.repository.UserRepository

class GetUserFollowersUseCase(
    private val repository: UserRepository
) {
    suspend fun execute(login: String) : List<User> =
        repository.getUserFollowers(login = login)
}