package com.codexdroid.theguru.di.network

import com.codexdroid.theguru.utility.PrefManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor() : Interceptor {

    @Inject lateinit var prefManager: PrefManager

    override fun intercept(chain: Interceptor.Chain): Response {

        val request = chain.request()
            .newBuilder()
        /**request.header("Authorization", prefManager.getToken() ?: "")**/
        return chain.proceed(request.build())
    }
}