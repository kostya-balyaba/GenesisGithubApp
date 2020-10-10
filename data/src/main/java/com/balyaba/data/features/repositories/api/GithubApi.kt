package com.balyaba.data.features.repositories.api

import com.balyaba.data.features.repositories.api.dto.RepositoriesResponse
import retrofit2.http.GET
import retrofit2.http.Query


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

interface GithubApi {

    @GET("search/repositories?sort=stars")
    suspend fun searchRepos(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RepositoriesResponse
}