package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.domain.models.LeadDetailsDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface FetchLeadByIdUseCase {

    suspend operator fun invoke(leadId: Int): LeadDetailsDomain
}

class FetchLeadByIdUseCaseImpl(
    private val repository: LeadRepository
) : FetchLeadByIdUseCase {

    override suspend fun invoke(leadId: Int): LeadDetailsDomain = repository.fetchLeadById(leadId)
}