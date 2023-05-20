package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.domain.models.CountryDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface FetchCountriesUseCase {

    suspend operator fun invoke(): List<CountryDomain>
}

class FetchCountriesUseCaseImpl(
    private val repository: LeadRepository
) : FetchCountriesUseCase {

    override suspend fun invoke(): List<CountryDomain> = repository.fetchCountries()
}