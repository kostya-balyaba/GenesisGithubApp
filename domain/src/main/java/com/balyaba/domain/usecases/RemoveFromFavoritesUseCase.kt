package com.balyaba.domain.usecases

import com.balyaba.domain.entities.Repository
import com.balyaba.domain.repository.GithubRepository
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 12.10.2020
 */

class RemoveFromFavoritesUseCase @Inject constructor(
    private val githubRepository: GithubRepository
) {
    suspend fun remove(repository: Repository) {
        githubRepository.removeFromFavorites(repository.id)
    }
}