package com.balyaba.data.features.repositories.mapper

import com.balyaba.data.common.Mapper
import com.balyaba.data.features.repositories.local.dto.RepositoryCacheDto
import com.balyaba.domain.entities.Repository
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

class RepositoryDomainToCacheMapper @Inject constructor() :
    Mapper<Repository, RepositoryCacheDto> {
    override fun mapFromObject(source: Repository): RepositoryCacheDto =
        with(source) {
            RepositoryCacheDto(
                id,
                name,
                fullName,
                description,
                url,
                stars,
                forks,
                language,
                ownerAvatarUrl,
                isFavorite
            )
        }
}