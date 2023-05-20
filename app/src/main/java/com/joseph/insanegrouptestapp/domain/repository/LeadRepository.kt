package com.joseph.insanegrouptestapp.domain.repository

import com.joseph.insanegrouptestapp.common.ResponseStatus
import com.joseph.insanegrouptestapp.domain.models.CityDomain
import com.joseph.insanegrouptestapp.domain.models.CountryDomain
import com.joseph.insanegrouptestapp.domain.models.CreateLeadDomain
import com.joseph.insanegrouptestapp.domain.models.LanguageDomain
import com.joseph.insanegrouptestapp.domain.models.LeadDetailsDomain
import com.joseph.insanegrouptestapp.domain.models.LeadDomain
import com.joseph.insanegrouptestapp.domain.models.LeadIntentionTypeDomain
import com.joseph.insanegrouptestapp.domain.models.LeadSourcesDomain

interface LeadRepository {

    suspend fun fetchAllLeads(): List<LeadDomain>

    suspend fun fetchLeadById(leadId: Int): LeadDetailsDomain

    suspend fun fetchLeadIntentionType(): List<LeadIntentionTypeDomain>

    suspend fun fetchCountries(): List<CountryDomain>

    suspend fun fetchLanguages(): List<LanguageDomain>

    suspend fun fetchLeadSources(): List<LeadSourcesDomain>

    suspend fun fetchCities(countryId: Int): List<CityDomain>

    suspend fun createNewLead(createLeadDomain: CreateLeadDomain): ResponseStatus<String>

}