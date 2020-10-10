package com.balyaba.domain.entities


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

data class Repository(
    val id: Long,
    val name: String?,
    val fullName: String?,
    val description: String?,
    val url: String?,
    val stars: Int?,
    val forks: Int?,
    val language: String?
)