package com.balyaba.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.balyaba.data.features.repositories.local.dto.RepositoryCacheDto


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Database(
    entities = [RepositoryCacheDto::class],
    version = 1
)
abstract class GithubDatabase : RoomDatabase() {

    abstract fun repositoriesDao(): GithubDao
}