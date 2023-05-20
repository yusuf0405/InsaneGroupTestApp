package com.joseph.insanegrouptestapp.data.source

import com.joseph.insanegrouptestapp.common.ResponseStatus
import com.joseph.insanegrouptestapp.data.models.CityData
import com.joseph.insanegrouptestapp.data.models.CountryData
import com.joseph.insanegrouptestapp.data.models.CreateLeadData
import com.joseph.insanegrouptestapp.data.models.LanguageData
import com.joseph.insanegrouptestapp.data.models.LeadData
import com.joseph.insanegrouptestapp.data.models.LeadDetailsData
import com.joseph.insanegrouptestapp.data.models.LeadIntentionTypeData
import com.joseph.insanegrouptestapp.data.models.LeadSourcesData

interface LeadCloudDataSource {

    suspend fun fetchLeads(): List<LeadData>

    suspend fun fetchLeadById(leadId: Int): LeadDetailsData

    suspend fun fetchLeadIntentionType(): List<LeadIntentionTypeData>

    suspend fun fetchCountries(): List<CountryData>

    suspend fun fetchCities(countryId: Int): List<CityData>

    suspend fun fetchLanguages(): List<LanguageData>

    suspend fun fetchLeadSources(): List<LeadSourcesData>

    suspend fun createNewLead(createLeadData: CreateLeadData): ResponseStatus<String>

}