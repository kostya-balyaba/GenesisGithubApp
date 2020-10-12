package com.balyaba.genesisgithubapp.features.repositories.adapter.datasource

import androidx.paging.PageKeyedDataSource
import com.balyaba.data.features.repositories.api.dto.NetworkState
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.usecases.GetFavoritesUseCase
import com.balyaba.domain.usecases.GetRepositoriesUseCase
import kotlinx.coroutines.*


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class AdapterDataSource(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val coroutineScope: CoroutineScope,
    private val query: String,
    private val stateCallback: AdapterDataSourceCallback
) : PageKeyedDataSource<Int, Repository>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Repository>
    ) {
        if (query.isEmpty())
            stateCallback.processNetworkState(NetworkState.SUCCESS_EMPTY)
        else
            executeQuery(1, params.requestedLoadSize) {
                callback.onResult(it, null, 3)
            }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        val page = params.key
        executeQuery(page, params.requestedLoadSize) {
            callback.onResult(it, page + 2)
        }
    }

    private fun executeQuery(page: Int, perPage: Int, callback: (List<Repository>) -> Unit) {
        stateCallback.processNetworkState(NetworkState.LOADING)
        coroutineScope.launch(getJobErrorHandler()) {
            val first = async {
                getRepositoriesUseCase.getRepositoriesList(query, page, perPage)
            }
            val second = async {
                getRepositoriesUseCase.getRepositoriesList(query, page + 1, perPage)
            }
            val resultList = mutableListOf<Repository>()

            resultList.addAll(first.await())
            resultList.addAll(second.await())

            withContext(Dispatchers.IO) {
                val favoritesRepositories = getFavoritesUseCase.getFavorites()
                resultList.forEach { repository ->
                    favoritesRepositories.forEach {
                        if (repository.id == it.id)
                            repository.isFavorite = true
                    }
                }
            }

            if (resultList.isEmpty())
                stateCallback.processNetworkState(NetworkState.SUCCESS_EMPTY)
            else
                stateCallback.processNetworkState(NetworkState.SUCCESS_LOADED)

            callback(resultList)
        }
    }

    private fun getJobErrorHandler() = CoroutineExceptionHandler { _, e ->
        stateCallback.processNetworkState(NetworkState.ERROR)
    }
}