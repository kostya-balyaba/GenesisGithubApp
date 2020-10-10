package com.balyaba.data.common.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by Baliaba Konstantin on 10.10.2020
 */

class ServerErrorsInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response: Response?
        try {
            response = chain.proceed(request)
        } catch (e: Exception) {
            throw ServerError
        }

        return response
    }
}
