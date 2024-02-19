package com.arfest.githubprofiles.data.repository

import com.arfest.githubprofiles.data.mappers.toDomain
import com.arfest.githubprofiles.data.mappers.toStorage
import com.arfest.githubprofiles.data.network.api.GitApi
import com.arfest.githubprofiles.data.storage.api.ProfileStorage
import com.arfest.githubprofiles.domain.models.User
import com.arfest.githubprofiles.domain.models.UserProfile
import com.arfest.githubprofiles.domain.repository.UserRepository
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class UserRepositoryImpl(
    private val profileStorage: ProfileStorage,
    private val gitApi: GitApi,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    private var loginIntoUsers: String = "mojodna"

    override suspend fun getUsers(): List<User> =
        withContext(dispatcher){
            try {
                gitApi.getUsers().await()
            } catch (cause: Throwable) {
                if(cause is CancellationException) throw cause
                throw Exception(cause)
            }
        }


    override suspend fun getUserProfile(login: String): UserProfile {
        loginIntoUsers = login
        return withContext(dispatcher) {

            try {
                val result = gitApi.getUser(login).toDomain()


                result
            } catch (cause: Throwable) {
                if (cause is CancellationException) throw cause
                throw Exception(cause)
            }
        }
    }

    override suspend fun getUserProfile(): Flow<UserProfile> =
        profileStorage.observe(loginIntoUsers).flowOn(
            dispatcher
        ).map { it.toDomain() }

    override suspend fun getUserFollowers(login: String): List<User> =
        withContext(dispatcher){
            try {
                gitApi.getUsers(login).await()
            } catch (cause: Throwable) {
                if(cause is CancellationException) throw cause
                throw Exception(cause)
            }
        }

}



class Exception(cause: Throwable) : Throwable(cause = cause)