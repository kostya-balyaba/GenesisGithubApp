package com.balyaba.genesisgithubapp.features.repositories

import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.balyaba.data.features.repositories.api.dto.NetworkState
import com.balyaba.domain.usecases.GetRepositoriesUseCase
import com.balyaba.genesisgithubapp.common.vm.BaseViewModel
import com.balyaba.genesisgithubapp.features.repositories.adapter.datasource.AdapterDataSourceCallback
import com.balyaba.genesisgithubapp.features.repositories.adapter.datasource.AdapterDataSourceFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : BaseViewModel<RepositoriesViewState, RepositoriesViewEffect, RepositoriesViewEvent>(),
    AdapterDataSourceCallback {

    private val repositoriesDataSource =
        AdapterDataSourceFactory(getRepositoriesUseCase, viewModelScope, stateCallback = this)

    private var startQueryJob: Job? = null

    val repositories = LivePagedListBuilder(repositoriesDataSource, pagedListConfig()).build()

    init {
        viewState = RepositoriesViewState(Status.Loading)
    }

    override fun processEvent(viewEvent: RepositoriesViewEvent) {
        when (viewEvent) {
            is RepositoriesViewEvent.OnSearchRequest -> {
                startSearchQuery(viewEvent.query)
            }
        }
    }

    override fun processNetworkState(state: NetworkState) {
        viewState = when (state) {
            NetworkState.LOADING -> {
                viewState.copy(status = Status.Loading)
            }
            NetworkState.SUCCESS -> {
                viewState.copy(status = Status.Success)
            }
            NetworkState.ERROR -> {
                viewState.copy(status = Status.Error)
            }
        }
    }

    private fun startSearchQuery(query: String) {
        startQueryJob?.cancel()
        startQueryJob = viewModelScope.launch {
            delay(SEARCH_REQUEST_DELAY)
            repositoriesDataSource.updateQuery(query.trim())
        }
    }

    private fun pagedListConfig() = PagedList.Config.Builder()
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setEnablePlaceholders(false)
        .setPageSize(PAGE_SIZE)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .build()

    companion object {
        const val PAGE_SIZE = 15
        const val PREFETCH_DISTANCE = 5
        const val INITIAL_LOAD_SIZE_HINT = 15
        const val SEARCH_REQUEST_DELAY = 600L
    }
}