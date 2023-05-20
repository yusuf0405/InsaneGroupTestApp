package com.joseph.insanegrouptestapp.data.client

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.joseph.type.DateTime
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor

class ApolloClientProviderImpl : ApolloClientProvider {

    override fun fetchApolloClient(): ApolloClient = setupApollo()

    private fun httpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

    private class AuthorizationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("Authorization", ACCESS_TOKEN)
                .build()
            return chain.proceed(request)
        }
    }


    private fun setupApollo(): ApolloClient = ApolloClient
        .Builder()
        .serverUrl(BASE_URL)
        .okHttpClient(
            OkHttpClient.Builder()
                .addInterceptor(AuthorizationInterceptor())
                .addInterceptor(httpLoggingInterceptor())
                .build()
        )
        .addCustomScalarAdapter(
            customScalarType = DateTime.type,
            customScalarAdapter = DateAdapter
        )
        .build()

    private companion object {
        const val ACCESS_TOKEN =
            "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlSWQiOjE0MzEsImZJZCI6IkV4bzhuYmJoMWkzV0l0algiLCJzSWQiOjUzLCJpYXQiOjE2ODM4MDU2MzQsImV4cCI6MTY4NTAxNTIzNH0.ndsOWw5VjFf_ZcpwYmTd-9sgQpwhlohNDUIok-8PE7w"

        const val BASE_URL = "http://54.246.238.84:3000/graphql"
    }

}