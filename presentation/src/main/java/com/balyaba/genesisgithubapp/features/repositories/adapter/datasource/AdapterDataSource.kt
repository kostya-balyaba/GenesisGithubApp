package com.balyaba.genesisgithubapp.features.repositories.adapter.datasource

import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.balyaba.domain.entities.Repository
import com.balyaba.domain.usecases.GetRepositoriesUseCase


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class AdapterDataSource(private val getRepositoriesUseCase: GetRepositoriesUseCase) :
    PageKeyedDataSource<Int, Repository>() {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Repository>
    ) {
        Log.d("asd", "loadInitial")
        callback.onResult(test(),1,2)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        Log.d("asd", "loadBefore")
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repository>) {
        Log.d("asd", "loadAfter")
    }

    fun test(): List<Repository> {
        val a = mutableListOf<Repository>()
        for (i in 0..39 step 1)
            a.add(Repository(1,"asd","asd","asd","asd",2,3,"asd"))
        return a
    }
}