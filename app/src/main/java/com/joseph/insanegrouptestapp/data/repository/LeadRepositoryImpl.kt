package com.joseph.insanegrouptestapp.data.repository

import com.joseph.insanegrouptestapp.data.mappers.CityDataToCityDomainMapper
import com.joseph.insanegrouptestapp.data.mappers.CountryDataToCountryDomainMapper
import com.joseph.insanegrouptestapp.data.mappers.CreateLeadDomainToCreateLeadDataMapper
import com.joseph.insanegrouptestapp.data.mappers.LanguageDomainToLanguageDataMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadDataToLeadDomainMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadDetailsDataToLeadDetailsDomainMapper
import com.joseph.insanegrouptestapp.data.source.LeadCloudDataSource
import com.joseph.insanegrouptestapp.domain.models.CityDomain
import com.joseph.insanegrouptestapp.domain.models.CountryDomain
import com.joseph.insanegrouptestapp.domain.models.CreateLeadDomain
import com.joseph.insanegrouptestapp.domain.models.LanguageDomain
import com.joseph.insanegrouptestapp.domain.models.LeadDetailsDomain
import com.joseph.insanegrouptestapp.domain.models.LeadDomain
import com.joseph.insanegrouptestapp.domain.models.LeadIntentionTypeDomain
import com.joseph.insanegrouptestapp.domain.models.LeadSourcesDomain
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository

class LeadRepositoryImpl(
    private val leadCloudDataSource: LeadCloudDataSource,
    private val countryDataToCountryDomainMapper: CountryDataToCountryDomainMapper,
    private val cityDataToCityDomainMapper: CityDataToCityDomainMapper,
    private val languageDomainToLanguageDataMapper: LanguageDomainToLanguageDataMapper,
    private val createLeadDomainToCreateLeadDataMapper: CreateLeadDomainToCreateLeadDataMapper,
    private val leadDataToLeadDomainMapper: LeadDataToLeadDomainMapper,
    private val leadDetailsDataToLeadDetailsDomainMapper: LeadDetailsDataToLeadDetailsDomainMapper,
) : LeadRepository {

    override suspend fun fetchAllLeads(): List<LeadDomain> =
        leadCloudDataSource.fetchLeads()
            .map(leadDataToLeadDomainMapper::map)

    override suspend fun fetchLeadById(leadId: Int): LeadDetailsDomain =
        leadDetailsDataToLeadDetailsDomainMapper
            .map(leadCloudDataSource.fetchLeadById(leadId = leadId))


    override suspend fun fetchLeadIntentionType(): List<LeadIntentionTypeDomain> =
        leadCloudDataSource.fetchLeadIntentionType()
            .map { LeadIntentionTypeDomain(id = it.id, title = it.title) }

    override suspend fun fetchCountries(): List<CountryDomain> =
        leadCloudDataSource.fetchCountries()
            .map(countryDataToCountryDomainMapper::map)

    override suspend fun fetchLanguages(): List<LanguageDomain> =
        leadCloudDataSource.fetchLanguages()
            .map(languageDomainToLanguageDataMapper::map)

    override suspend fun fetchLeadSources(): List<LeadSourcesDomain> =
        leadCloudDataSource.fetchLeadSources()
            .map { LeadSourcesDomain(id = it.id, title = it.title) }

    override suspend fun fetchCities(countryId: Int): List<CityDomain> =
        leadCloudDataSource.fetchCities(countryId = countryId)
            .map(cityDataToCityDomainMapper::map)

    override suspend fun createNewLead(createLeadDomain: CreateLeadDomain) =
        leadCloudDataSource.createNewLead(
            createLeadDomainToCreateLeadDataMapper.map(
                createLeadDomain
            )
        )
}