package com.balyaba.genesisgithubapp.features.repositories.adapter.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.usecases.GetFavoritesUseCase
import com.balyaba.domain.usecases.GetRepositoriesUseCase
import kotlinx.coroutines.CoroutineScope


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class AdapterDataSourceFactory(
    private val getRepositoriesUseCase: GetRepositoriesUseCase,
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val coroutineScope: CoroutineScope,
    private var query: String = "",
    private val stateCallback: AdapterDataSourceCallback
) : DataSource.Factory<Int, Repository>() {

    private val source = MutableLiveData<AdapterDataSource>()

    override fun create(): DataSource<Int, Repository> {
        val source = AdapterDataSource(getRepositoriesUseCase, getFavoritesUseCase, coroutineScope, query, stateCallback)
        this.source.postValue(source)
        return source
    }

    private fun getSource(): AdapterDataSource? = source.value

    fun updateQuery(query: String) {
        this.query = query
        getSource()?.invalidate()
    }
}