package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.domain.models.LeadIntentionTypeDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface FetchLeadIntentionTypes {

    suspend operator fun invoke(): List<LeadIntentionTypeDomain>
}

class FetchLeadIntentionTypesImpl(
    private val repository: LeadRepository
) : FetchLeadIntentionTypes {

    override suspend fun invoke(): List<LeadIntentionTypeDomain> =
        repository.fetchLeadIntentionType()
}