package com.arfest.githubprofiles.data.mappers

import com.arfest.githubprofiles.data.network.model.UserProfileNetwork
import com.arfest.githubprofiles.data.storage.model.ProfileEntity
import com.arfest.githubprofiles.domain.models.UserProfile

internal fun ProfileEntity.toDomain(): UserProfile {
    return UserProfile(
        login = login,
        image = image,
        name = name,
        company = company,
        email = email,
        blog = blog,
        location = location,
        bio = bio
    )
}
