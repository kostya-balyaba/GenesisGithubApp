package com.balyaba.data.common

/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

interface Mapper<S, D> {

    fun mapFromObject(source: S): D

    fun mapFromObjects(sources: Collection<S>) = sources.map { mapFromObject(it) }
}