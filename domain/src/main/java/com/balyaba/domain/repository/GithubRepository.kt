package com.balyaba.domain.repository

import com.balyaba.domain.entities.Repository


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

interface GithubRepository {

    suspend fun getRepositoriesList(query: String, page: Int, itemsPerPage: Int): List<Repository>
}