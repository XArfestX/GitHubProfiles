package com.arfest.githubprofiles.data.storage.model

data class ProfileEntity (
    val login: String,
    val image: String,
    val name: String,
    val company: String,
    val email: String,
    val blog: String,
    val location: String,
    val bio: String
)