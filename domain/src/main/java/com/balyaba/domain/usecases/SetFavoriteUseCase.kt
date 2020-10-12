package com.balyaba.domain.usecases

import com.balyaba.domain.entities.Repository
import com.balyaba.domain.repository.GithubRepository
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

class SetFavoriteUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun addInFavorites(repository: Repository) {
        githubRepository.addFavoriteRepository(repository)
    }
}