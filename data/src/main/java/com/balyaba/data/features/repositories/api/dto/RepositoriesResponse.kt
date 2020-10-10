package com.balyaba.data.features.repositories.api.dto

import com.google.gson.annotations.SerializedName


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

data class RepositoriesResponse(
    @SerializedName("total_count") val total: Int = 0,
    @SerializedName("items") val items: List<RepositoryDto> = emptyList()
)