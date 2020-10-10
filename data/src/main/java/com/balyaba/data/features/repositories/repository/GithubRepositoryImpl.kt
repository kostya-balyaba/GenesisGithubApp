package com.balyaba.data.features.repositories.repository

import com.balyaba.data.common.Mapper
import com.balyaba.data.features.repositories.api.dto.RepositoryDto
import com.balyaba.data.features.repositories.datasource.GithubLocalDataSource
import com.balyaba.data.features.repositories.datasource.GithubRemoteDataSource
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.repository.GithubRepository
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class GithubRepositoryImpl @Inject constructor(
    private val localDataSource: GithubLocalDataSource,
    private val remoteDataSource: GithubRemoteDataSource,
    private val toDomainMapper: Mapper<RepositoryDto, Repository>
) : GithubRepository {

    override suspend fun getRepositoriesList(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): List<Repository> {
        val repositoriesResponse = remoteDataSource.getRepositories(query, page, itemsPerPage)
        return toDomainMapper.mapFromObjects(repositoriesResponse.items)
    }
}