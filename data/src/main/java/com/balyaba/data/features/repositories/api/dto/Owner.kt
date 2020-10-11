package com.balyaba.data.features.repositories.api.dto

import com.google.gson.annotations.SerializedName


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

data class Owner(
    @SerializedName("avatar_url") val avatarUrl: String?
) 