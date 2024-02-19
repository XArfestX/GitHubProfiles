package com.arfest.githubprofiles.data.storage.memory

import com.arfest.githubprofiles.data.storage.api.ProfileStorage
import com.arfest.githubprofiles.data.storage.model.ProfileEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class ProfileMemoryStorage : ProfileStorage {

    private var count: Int = 0

    private var profileItem: MutableSet<ProfileEntity> = mutableSetOf()
    private val profileItemsState: MutableStateFlow<List<ProfileEntity>> = MutableStateFlow(listOf(
        ProfileEntity(
            login = "mojodna",
            image = "https://avatars.githubusercontent.com/u/1060?v=4",
            name = "Dave",
            company = "github",
            email = "dogy1980@gmail.com",
            blog = "",
            location = "USA",
            bio = "I am an ordinary programmer from America :)"
        )
    ))

    override fun add(profile: ProfileEntity) {
        profileItem.add(profile)
        count ++
        profileItemsState.update {
            profileItem.toList()
        }
    }

    override suspend fun observe(login: String): Flow<ProfileEntity> = profileItemsState.map { it[count] }


}