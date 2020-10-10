package com.balyaba.genesisgithubapp.features.repositories

import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.balyaba.domain.usecases.GetRepositoriesUseCase
import com.balyaba.genesisgithubapp.features.repositories.adapter.datasource.AdapterDataSourceFactory
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val repositoriesDataSource = AdapterDataSourceFactory(getRepositoriesUseCase)
    val repositories = LivePagedListBuilder(repositoriesDataSource, pagedListConfig()).build()

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(40)
        .setEnablePlaceholders(false)
        .setPageSize(30)
        .setPrefetchDistance(5)
        .build()
}