package com.balyaba.data.di

import com.balyaba.data.features.repositories.repository.GithubRepositoryImpl
import com.balyaba.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Module(includes = [DataSourceModule::class])
interface RepositoriesModule {

    @Binds
    @Singleton
    fun bindGithubRepository(repo: GithubRepositoryImpl): GithubRepository
}