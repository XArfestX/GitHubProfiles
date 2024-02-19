package com.arfest.githubprofiles.data.network.api

import com.arfest.githubprofiles.data.network.model.UserProfileNetwork
import com.arfest.githubprofiles.domain.models.User
import kotlinx.coroutines.Deferred

interface GitApi {
    suspend fun getUsers(): Deferred<MutableList<User>>
    suspend fun getUsers(login: String): Deferred<MutableList<User>>
    fun getUser(login: String): UserProfileNetwork
    fun getFollowersAndRepos(login: String): List<Int>
}