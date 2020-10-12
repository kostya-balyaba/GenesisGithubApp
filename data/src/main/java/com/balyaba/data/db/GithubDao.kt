package com.balyaba.data.db

import androidx.room.*
import com.balyaba.data.features.repositories.local.dto.RepositoryCacheDto


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Dao
interface GithubDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addInFavorites(repository: RepositoryCacheDto)

    @Query("SELECT * FROM repository")
    suspend fun getFavorites(): List<RepositoryCacheDto>

    @Query("DELETE FROM repository WHERE id = :id")
    suspend fun removeFromFavorites(id: Long)
}