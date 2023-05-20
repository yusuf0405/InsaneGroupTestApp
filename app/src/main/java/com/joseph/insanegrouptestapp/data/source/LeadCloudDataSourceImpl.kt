package com.joseph.insanegrouptestapp.data.source

import com.joseph.CitiesQuery
import com.joseph.CountryQuery
import com.joseph.CreateLeadMutation
import com.joseph.LanguagesQuery
import com.joseph.LeadIntentionTypesQuery
import com.joseph.LeadQuery
import com.joseph.LeadSourcesQuery
import com.joseph.LeadsQuery
import com.joseph.insanegrouptestapp.common.ResponseStatus
import com.joseph.insanegrouptestapp.data.client.ApolloClientProvider
import com.joseph.insanegrouptestapp.data.mappers.CreateLeadDataToCreateLeadInputMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadDetailsCloudToLeadDetailsDataMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadQueryToLeadDataMapper
import com.joseph.insanegrouptestapp.data.models.CityData
import com.joseph.insanegrouptestapp.data.models.CountryData
import com.joseph.insanegrouptestapp.data.models.CreateLeadData
import com.joseph.insanegrouptestapp.data.models.LanguageData
import com.joseph.insanegrouptestapp.data.models.LeadData
import com.joseph.insanegrouptestapp.data.models.LeadDetailsData
import com.joseph.insanegrouptestapp.data.models.LeadIntentionTypeData
import com.joseph.insanegrouptestapp.data.models.LeadSourcesData
import com.joseph.type.FetchLeadInput

class LeadCloudDataSourceImpl(
    private val apolloClientProvider: ApolloClientProvider,
    private val createLeadDataToCreateLeadInputMapper: CreateLeadDataToCreateLeadInputMapper,
    private val leadQueryToLeadDataMapper: LeadQueryToLeadDataMapper,
    private val leadDetailsCloudToLeadDetailsDataMapper: LeadDetailsCloudToLeadDetailsDataMapper,
) : LeadCloudDataSource {

    private fun apolloClient() = apolloClientProvider.fetchApolloClient()

    override suspend fun fetchLeads(): List<LeadData> = apolloClient()
        .query(LeadsQuery())
        .execute()
        .data
        ?.fetchLeads
        ?.data
        ?.map { leadQueryToLeadDataMapper.map(it) } ?: emptyList()

    override suspend fun fetchLeadById(leadId: Int): LeadDetailsData {
        val lead = apolloClient()
            .query(LeadQuery(FetchLeadInput(leadId)))
            .execute()
            .data
            ?.fetchLead
            ?.data
        return leadDetailsCloudToLeadDetailsDataMapper.map(lead)
    }


    override suspend fun fetchLeadIntentionType(): List<LeadIntentionTypeData> =
        apolloClient()
            .query(LeadIntentionTypesQuery())
            .execute()
            .data
            ?.fetchLeadIntentionTypes
            ?.map { lead ->
                LeadIntentionTypeData(
                    id = lead.id,
                    title = lead.title
                )
            } ?: emptyList()


    override suspend fun fetchCountries(): List<CountryData> =
        apolloClient()
            .query(CountryQuery())
            .execute()
            .data
            ?.fetchCountries
            ?.map { country ->
                CountryData(
                    id = country.id,
                    shortCode1 = country.shortCode1,
                    shortCode2 = country.shortCode2,
                    title = country.title,
                    emoji = country.emoji ?: String(),
                    adWordsCode = country.adWordsCode ?: 0
                )
            } ?: emptyList()


    override suspend fun fetchCities(countryId: Int): List<CityData> =
        apolloClient()
            .query(CitiesQuery(countryId))
            .execute()
            .data
            ?.cities
            ?.map { city ->
                CityData(
                    id = city.id,
                    countryId = city.countryId,
                    offset = city.offset,
                    offsetMs = city.offsetMs,
                    title = city.title,
                    timezone = city.timezone
                )
            } ?: emptyList()

    override suspend fun fetchLanguages(): List<LanguageData> =
        apolloClient()
            .query(LanguagesQuery())
            .execute()
            .data
            ?.languages
            ?.map { language ->
                LanguageData(
                    id = language.id,
                    title = language.title,
                    shortCode = language.shortCode
                )
            } ?: emptyList()

    override suspend fun fetchLeadSources(): List<LeadSourcesData> =
        apolloClient()
            .query(LeadSourcesQuery())
            .execute()
            .data
            ?.fetchLeadSources
            ?.map { leadSources ->
                LeadSourcesData(
                    id = leadSources.id,
                    title = leadSources.title
                )
            } ?: emptyList()

    override suspend fun createNewLead(createLeadData: CreateLeadData): ResponseStatus<String> {
        return try {
            apolloClient()
                .mutation(
                    CreateLeadMutation(createLeadDataToCreateLeadInputMapper.map(createLeadData))
                ).execute()
                .data
                ?.createLead
                ?.data
            ResponseStatus.Success("")

        } catch (e: Exception) {
            ResponseStatus.Error(message = "", error = e)
        }

    }


}