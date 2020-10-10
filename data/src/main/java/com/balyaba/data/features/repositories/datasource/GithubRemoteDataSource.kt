package com.balyaba.data.features.repositories.datasource

import com.balyaba.data.features.repositories.api.dto.RepositoriesResponse
import com.balyaba.data.features.repositories.api.dto.RepositoryDto


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

interface GithubRemoteDataSource {

    suspend fun getRepositories(query: String, page: Int, itemsPerPage: Int): RepositoriesResponse
}