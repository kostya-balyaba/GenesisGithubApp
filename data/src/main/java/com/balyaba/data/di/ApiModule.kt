package com.balyaba.data.di

import com.balyaba.data.features.repositories.api.GithubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Module(includes = [NetworkModule::class])
class ApiModule {

    @Provides
    fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)
}