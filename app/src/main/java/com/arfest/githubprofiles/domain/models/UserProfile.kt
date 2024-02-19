package com.arfest.githubprofiles.domain.models

data class UserProfile(
    val login: String,
    val image: String,
    val name: String,
    val company: String,
    val email: String,
    val blog: String,
    val location: String,
    val bio: String
)
