package com.balyaba.data.features.repositories.datasource

import com.balyaba.data.features.repositories.local.dto.RepositoryCacheDto


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

interface GithubLocalDataSource {
    suspend fun addInFavorites(repository: RepositoryCacheDto)

    suspend fun getFavorites(): List<RepositoryCacheDto>

    suspend fun removeFromFavorites(id: Long)
}