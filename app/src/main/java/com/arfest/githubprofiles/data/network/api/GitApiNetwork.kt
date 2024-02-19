package com.arfest.githubprofiles.data.network.api

import com.arfest.githubprofiles.data.mappers.toStorage
import com.arfest.githubprofiles.data.network.model.UserFollowersNetwork
import com.arfest.githubprofiles.data.network.model.UserNetwork
import com.arfest.githubprofiles.data.network.model.UserProfileNetwork
import com.arfest.githubprofiles.data.network.model.UserReposNetwork
import com.arfest.githubprofiles.data.storage.api.ProfileStorage
import com.arfest.githubprofiles.domain.models.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class GitApiNetwork(
    private val profileStorage: ProfileStorage,
    private val apiUsersUrl: String,
    private val moshi: Moshi,
    private val dispatcher: CoroutineDispatcher
) : GitApi {

    override suspend fun getUsers(): Deferred<MutableList<User>> = coroutineScope {
        return@coroutineScope async(dispatcher) {
            val url = URL(apiUsersUrl)
            val con = withContext(dispatcher) {
                url.openConnection()
            } as HttpURLConnection
            val json = con.inputStream.bufferedReader().use { it.readText() }

            val listType = Types.newParameterizedType(List::class.java, UserNetwork::class.java)
            val adapter: JsonAdapter<List<UserNetwork>> = moshi.adapter(listType)
            val response = adapter.fromJson(json)

            val result = mutableListOf<User>()
            val deferredUsers = response?.map { item ->

                val counts = getFollowersAndRepos(item.url)

                User(
                    login = item.login,
                    image = item.avatar_url,
                    countFollowers = counts[0],
                    countRepos = counts[1]
                )
            }
            result.addAll(deferredUsers!!)

            return@async result
        }
    }

    override suspend fun getUsers(login: String): Deferred<MutableList<User>>  = coroutineScope {
        return@coroutineScope async(dispatcher) {
            val url = URL(apiUsersUrl + "/" + login + "/followers")
            println(url)
            val con = withContext(dispatcher) {
                url.openConnection()
            } as HttpURLConnection
            val json = con.inputStream.bufferedReader().use { it.readText() }

            val listType = Types.newParameterizedType(List::class.java, UserNetwork::class.java)
            val adapter: JsonAdapter<List<UserNetwork>> = moshi.adapter(listType)
            val response = adapter.fromJson(json)


            val result = mutableListOf<User>()
            val deferredUsers = response?.map { item ->

                val counts = getFollowersAndRepos(item.url)

                User(
                    login = item.login,
                    image = item.avatar_url,
                    countFollowers = counts[0],
                    countRepos = counts[1]
                )
            }
            result.addAll(deferredUsers!!)

            return@async result
        }
    }

    override fun getUser(url: String): UserProfileNetwork {
        val url = URL(url)
        println(url)

        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val jsonAdapter = moshi.adapter(UserProfileNetwork::class.java)

        return jsonAdapter.fromJson(json) as UserProfileNetwork
    }

    override fun getFollowersAndRepos(url: String): List<Int> {
        val url = URL(url)

        val con = url.openConnection() as HttpURLConnection
        val json = con.inputStream.bufferedReader().use { it.readText() }

        val jsonAdapter = moshi.adapter(UserProfileNetwork::class.java)

        val response = jsonAdapter.fromJson(json) as UserProfileNetwork
        profileStorage.add(response.toStorage())
        return  listOf(response.followers, response.public_repos)
    }

}


