package com.balyaba.genesisgithubapp.features.repositories.adapter.datasource

import com.balyaba.data.features.repositories.api.dto.NetworkState


/**
 * Created by Baliaba Konstantin on 11.10.2020
 */

interface AdapterDataSourceCallback {
    fun processNetworkState(state: NetworkState)
}