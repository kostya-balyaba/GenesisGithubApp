package com.balyaba.data.features.repositories.cache.dto

import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Entity(tableName = "repository")
data class RepositoryCacheDto(
    @PrimaryKey
    val id: Long,
    val name: String?,
    val fullName: String?,
    val description: String?,
    val url: String?,
    val stars: Int?,
    val forks: Int?,
    val language: String?
)