package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.domain.models.LanguageDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface FetchLanguagesUseCase {

    suspend operator fun invoke(): List<LanguageDomain>

}

class FetchLanguagesUseCaseImpl(
    private val repository: LeadRepository
) : FetchLanguagesUseCase {

    override suspend fun invoke(): List<LanguageDomain> = repository.fetchLanguages()
}