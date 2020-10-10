package com.balyaba.domain.usecases

import com.balyaba.domain.repository.GithubRepository
import javax.inject.Inject

/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class GetRepositoriesUseCase @Inject constructor(private val githubRepository: GithubRepository) {
    suspend fun getRepositoriesList(query: String, page: Int, itemsPerPage: Int) =
        githubRepository.getRepositoriesList(query, page, itemsPerPage)
}