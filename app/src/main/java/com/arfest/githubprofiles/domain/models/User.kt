package com.arfest.githubprofiles.domain.models

data class User(
    val login: String,
    val image: String,
    val countFollowers: Int,
    val countRepos: Int
)
