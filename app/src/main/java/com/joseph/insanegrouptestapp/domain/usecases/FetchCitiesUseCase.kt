package com.joseph.insanegrouptestapp.domain.usecases

import com.joseph.insanegrouptestapp.domain.models.CityDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

interface FetchCitiesUseCase {

    suspend operator fun invoke(countryId: Int): List<CityDomain>
}

class FetchCitiesUseCaseImpl(
    private val repository: LeadRepository
) : FetchCitiesUseCase {

    override suspend fun invoke(countryId: Int): List<CityDomain> =
        repository.fetchCities(countryId)
}