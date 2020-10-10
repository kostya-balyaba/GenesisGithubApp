package com.balyaba.data.features.repositories.mapper

import com.balyaba.data.common.Mapper
import com.balyaba.data.features.repositories.api.dto.RepositoryDto
import com.balyaba.domain.entities.Repository
import javax.inject.Inject


/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class RepositoryDtoToDomainMapper @Inject constructor() : Mapper<RepositoryDto, Repository> {
    override fun mapFromObject(source: RepositoryDto): Repository =
        with(source) {
            Repository(
                id,
                name,
                fullName,
                description,
                url,
                stars,
                forks,
                language
            )
        }
}