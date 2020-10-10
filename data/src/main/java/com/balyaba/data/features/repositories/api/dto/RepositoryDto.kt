package com.balyaba.data.features.repositories.api.dto

import com.google.gson.annotations.SerializedName


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

data class RepositoryDto(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String?,
    @SerializedName("fullName") val fullName: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("stars") val stars: Int?,
    @SerializedName("forks") val forks: Int?,
    @SerializedName("language") val language: String?
)