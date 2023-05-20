package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.common.ResponseStatus
import com.joseph.insanegrouptestapp.domain.models.CreateLeadDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface CreateNewLeadUseCase {

    suspend operator fun invoke(createLeadDomain: CreateLeadDomain): ResponseStatus<String>
}

class CreateNewLeadUseCaseImpl(
    private val repository: LeadRepository
) : CreateNewLeadUseCase {

    override suspend fun invoke(createLeadDomain: CreateLeadDomain): ResponseStatus<String> =
        repository.createNewLead(createLeadDomain)
}