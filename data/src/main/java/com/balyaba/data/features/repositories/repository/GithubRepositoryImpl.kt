package com.balyaba.data.features.repositories.repository

import com.balyaba.data.common.Mapper
import com.balyaba.data.features.repositories.api.dto.RepositoryDto
import com.balyaba.data.features.repositories.datasource.GithubLocalDataSource
import com.balyaba.data.features.repositories.datasource.GithubRemoteDataSource
import com.balyaba.data.features.repositories.local.dto.RepositoryCacheDto
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.repository.GithubRepository
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class GithubRepositoryImpl @Inject constructor(
    private val localDataSource: GithubLocalDataSource,
    private val remoteDataSource: GithubRemoteDataSource,
    private val toDomainMapper: Mapper<RepositoryDto, Repository>,
    private val cachedToDomainMapper: Mapper<RepositoryCacheDto, Repository>,
    private val toCachedMapper: Mapper<Repository, RepositoryCacheDto>
) : GithubRepository {

    override suspend fun getRepositoriesList(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): List<Repository> {
        val repositoriesResponse = remoteDataSource.getRepositories(query, page, itemsPerPage)
        return toDomainMapper.mapFromObjects(repositoriesResponse.items)
    }

    override suspend fun getFavoritesRepositoriesList(): List<Repository> {
        val cachedRepositories = localDataSource.getFavorites()
        return if (!cachedRepositories.isNullOrEmpty())
            cachedToDomainMapper.mapFromObjects(cachedRepositories)
        else emptyList()
    }

    override suspend fun addFavoriteRepository(repository: Repository) {
        localDataSource.addInFavorites(toCachedMapper.mapFromObject(repository))
    }

    override suspend fun removeFromFavorites(id: Long) {
        localDataSource.removeFromFavorites(id)
    }
}