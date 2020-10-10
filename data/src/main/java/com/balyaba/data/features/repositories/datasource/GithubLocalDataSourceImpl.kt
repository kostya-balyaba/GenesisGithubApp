package com.balyaba.data.features.repositories.datasource

import com.balyaba.data.db.GithubDao
import com.balyaba.data.features.repositories.api.dto.RepositoryDto
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class GithubLocalDataSourceImpl @Inject constructor(
    private val githubDao: GithubDao
) : GithubLocalDataSource