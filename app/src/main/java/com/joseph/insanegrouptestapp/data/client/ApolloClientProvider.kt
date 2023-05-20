package com.joseph.insanegrouptestapp.data.client

import com.apollographql.apollo3.ApolloClient

interface ApolloClientProvider {

    fun fetchApolloClient(): ApolloClient
}