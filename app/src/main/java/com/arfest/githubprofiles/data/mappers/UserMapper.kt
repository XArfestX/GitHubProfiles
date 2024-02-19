package com.arfest.githubprofiles.data.mappers

import com.arfest.githubprofiles.data.network.model.UserNetwork
import com.arfest.githubprofiles.domain.models.User

internal fun UserNetwork.toDomain(countFollowers: Int, countRepos: Int) =
    User(
        login = login,
        image = avatar_url,
        countFollowers = countFollowers,
        countRepos = countRepos,
    )