package com.balyaba.data.di

import android.content.Context
import androidx.room.Room
import com.balyaba.data.db.GithubDao
import com.balyaba.data.db.GithubDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun providesRepositoriesDatabase(context: Context): GithubDatabase =
        Room.databaseBuilder(context, GithubDatabase::class.java, "github_test.db").build()

    @Provides
    @Singleton
    fun provideRepositoriesDao(database: GithubDatabase): GithubDao =
        database.repositoriesDao()
}