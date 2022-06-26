package com.rodionovmax.mygithubapp.domain.model

data class Repo (
    val id: Long,
    val name: String,
    val url: String,
    val description: String?
)
