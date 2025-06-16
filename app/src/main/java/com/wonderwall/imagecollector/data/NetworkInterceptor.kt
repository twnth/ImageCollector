package com.wonderwall.imagecollector.data

import com.wonderwall.imagecollector.core.Constants.KAKAO_API_KEY
import okhttp3.Interceptor
import okhttp3.Response

class NetworkInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", "KakaoAK $KAKAO_API_KEY")
            .build()
        return chain.proceed(newRequest)
    }
}
