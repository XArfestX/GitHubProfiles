package com.arfest.githubprofiles.domain.usecases.users

import com.arfest.githubprofiles.domain.models.User
import com.arfest.githubprofiles.domain.repository.UserRepository

class GetUsersUseCase(
    private val repository:UserRepository
) {
    suspend fun execute() : List<User> =
        repository.getUsers()
}