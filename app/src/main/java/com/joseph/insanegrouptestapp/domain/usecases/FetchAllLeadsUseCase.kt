package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.domain.models.LeadDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface FetchAllLeadsUseCase {

    suspend operator fun invoke(): List<LeadDomain>
}

class FetchAllLeadsUseCaseImpl(
    private val repository: LeadRepository
) : FetchAllLeadsUseCase {

    override suspend fun invoke(): List<LeadDomain> = repository.fetchAllLeads()
}