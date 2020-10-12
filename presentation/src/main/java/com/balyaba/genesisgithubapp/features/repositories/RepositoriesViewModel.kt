package com.balyaba.genesisgithubapp.features.repositories

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.balyaba.data.features.repositories.api.dto.NetworkState
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.usecases.GetFavoritesUseCase
import com.balyaba.domain.usecases.GetRepositoriesUseCase
import com.balyaba.domain.usecases.RemoveFromFavoritesUseCase
import com.balyaba.domain.usecases.SetFavoriteUseCase
import com.balyaba.genesisgithubapp.common.vm.BaseViewModel
import com.balyaba.genesisgithubapp.features.repositories.adapter.RepositoriesAdapter
import com.balyaba.genesisgithubapp.features.repositories.adapter.datasource.AdapterDataSourceCallback
import com.balyaba.genesisgithubapp.features.repositories.adapter.datasource.AdapterDataSourceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val setFavoriteUseCase: SetFavoriteUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : BaseViewModel<RepositoriesViewState, RepositoriesViewEffect, RepositoriesViewEvent>(),
    AdapterDataSourceCallback {

    private val repositoriesDataSource =
        AdapterDataSourceFactory(
            getRepositoriesUseCase,
            getFavoritesUseCase,
            viewModelScope,
            stateCallback = this
        )

    private var startQueryJob: Job? = null

    val repositories = LivePagedListBuilder(repositoriesDataSource, pagedListConfig()).build()
    var lastQuery: String = ""

    init {
        viewState = RepositoriesViewState(Status.Loading)
    }

    override fun processEvent(viewEvent: RepositoriesViewEvent) {
        when (viewEvent) {
            is RepositoriesViewEvent.OnSearchRequestEvent -> {
                startSearchQuery(viewEvent.query)
            }
        }
    }

    val adapterListener = object : RepositoriesAdapter.OnItemClickListener {
        override fun onRepositoryClick(repository: Repository) {
            repository.url?.let { url ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                viewEffect = RepositoriesViewEffect.OnOpenLink(intent)
            }
        }

        override fun onFavoriteClick(repository: Repository, position: Int) {
            if (repository.isFavorite)
                removeFromFavorites(repository)
            else
                addInFavorites(repository)
            repository.isFavorite = repository.isFavorite.not()
            viewEffect = RepositoriesViewEffect.UpdateRepositoryByPosition(position)
        }
    }

    override fun processNetworkState(state: NetworkState) {
        viewState = when (state) {
            NetworkState.LOADING -> {
                viewState.copy(status = Status.Loading)
            }
            NetworkState.SUCCESS_LOADED -> {
                viewState.copy(status = Status.Success)
            }
            NetworkState.SUCCESS_EMPTY -> {
                viewState.copy(status = Status.Empty)
            }
            NetworkState.ERROR -> {
                viewState.copy(status = Status.Error)
            }
        }
    }

    private fun startSearchQuery(query: String) {
        if (query == lastQuery)
            return
        startQueryJob?.cancel()
        startQueryJob = viewModelScope.launch {
            delay(SEARCH_REQUEST_DELAY)
            lastQuery = query
            repositoriesDataSource.updateQuery(query.trim())
        }
    }

    private fun addInFavorites(repository: Repository) {
        viewModelScope.launch(Dispatchers.IO) {
            setFavoriteUseCase.addInFavorites(repository)
        }
    }

    private fun removeFromFavorites(repository: Repository) {
        viewModelScope.launch(Dispatchers.IO) {
            removeFromFavoritesUseCase.remove(repository)
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