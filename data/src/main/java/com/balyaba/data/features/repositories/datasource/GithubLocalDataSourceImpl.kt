package com.balyaba.data.features.repositories.datasource

import com.balyaba.data.db.GithubDao
import com.balyaba.data.features.repositories.local.dto.RepositoryCacheDto
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class GithubLocalDataSourceImpl @Inject constructor(
    private val githubDao: GithubDao
) : GithubLocalDataSource {

    override suspend fun addInFavorites(repository: RepositoryCacheDto) {
        githubDao.addInFavorites(repository)
    }

    override suspend fun getFavorites(): List<RepositoryCacheDto> = githubDao.getFavorites()

    override suspend fun removeFromFavorites(id: Long) {
        githubDao.removeFromFavorites(id)
    }
}