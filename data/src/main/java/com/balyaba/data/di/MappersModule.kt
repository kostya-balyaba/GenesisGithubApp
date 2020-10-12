package com.balyaba.data.di

import com.balyaba.data.common.Mapper
import com.balyaba.data.features.repositories.api.dto.RepositoryDto
import com.balyaba.data.features.repositories.local.dto.RepositoryCacheDto
import com.balyaba.data.features.repositories.mapper.RepositoryCachedDtoToDomainMapper
import com.balyaba.data.features.repositories.mapper.RepositoryDomainToCacheMapper
import com.balyaba.data.features.repositories.mapper.RepositoryDtoToDomainMapper
import com.balyaba.domain.entities.Repository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

@Module
abstract class MappersModule {

    @Binds
    @Singleton
    abstract fun provideRepositoryDtoToDomainMapper(mapper: RepositoryDtoToDomainMapper): Mapper<RepositoryDto, Repository>

    @Binds
    @Singleton
    abstract fun provideRepositoryCachedDtoToDomainMapper(mapper: RepositoryCachedDtoToDomainMapper): Mapper<RepositoryCacheDto, Repository>

    @Binds
    @Singleton
    abstract fun provideRepositoryDomainToCachedMapper(mapper: RepositoryDomainToCacheMapper): Mapper<Repository, RepositoryCacheDto>
}