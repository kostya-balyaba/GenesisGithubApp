package com.balyaba.genesisgithubapp.features.repositories.adapter.datasource

import androidx.paging.DataSource
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.usecases.GetRepositoriesUseCase


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class AdapterDataSourceFactory(private val getRepositoriesUseCase: GetRepositoriesUseCase) :
    DataSource.Factory<Int, Repository>() {

    override fun create(): DataSource<Int, Repository> {
        return AdapterDataSource(getRepositoriesUseCase)
    }
}