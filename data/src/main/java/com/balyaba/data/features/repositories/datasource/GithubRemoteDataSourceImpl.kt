package com.balyaba.data.features.repositories.datasource

import com.balyaba.data.features.repositories.api.GithubApi
import com.balyaba.data.features.repositories.api.dto.RepositoriesResponse
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class GithubRemoteDataSourceImpl @Inject constructor(
    private val githubApi: GithubApi
) : GithubRemoteDataSource {

    override suspend fun getRepositories(
        query: String,
        page: Int,
        itemsPerPage: Int
    ): RepositoriesResponse = githubApi.searchRepos(query, page, itemsPerPage)
}