package com.balyaba.data.di

import com.balyaba.data.features.repositories.datasource.GithubLocalDataSource
import com.balyaba.data.features.repositories.datasource.GithubLocalDataSourceImpl
import com.balyaba.data.features.repositories.datasource.GithubRemoteDataSource
import com.balyaba.data.features.repositories.datasource.GithubRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindGithubLocalDataSource(sourceImpl: GithubLocalDataSourceImpl): GithubLocalDataSource

    @Binds
    @Singleton
    fun bindGithubRemoteDataSource(sourceImpl: GithubRemoteDataSourceImpl): GithubRemoteDataSource
}