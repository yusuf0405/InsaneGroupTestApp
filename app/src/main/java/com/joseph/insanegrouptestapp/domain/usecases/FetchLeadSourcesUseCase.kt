package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.domain.models.LeadSourcesDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface FetchLeadSourcesUseCase {

    suspend operator fun invoke(): List<LeadSourcesDomain>

}

class FetchLeadSourcesUseCaseImpl(
    private val repository: LeadRepository,
) : FetchLeadSourcesUseCase {

    override suspend fun invoke(): List<LeadSourcesDomain> = repository.fetchLeadSources()
}