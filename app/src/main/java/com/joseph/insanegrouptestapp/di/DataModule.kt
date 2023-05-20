package com.joseph.insanegrouptestapp.di

import com.joseph.insanegrouptestapp.data.client.ApolloClientProvider
import com.joseph.insanegrouptestapp.data.client.ApolloClientProviderImpl
import com.joseph.insanegrouptestapp.data.mappers.CityDataToCityDomainMapper
import com.joseph.insanegrouptestapp.data.mappers.CountryDataToCountryDomainMapper
import com.joseph.insanegrouptestapp.data.mappers.CreateLeadDataToCreateLeadInputMapper
import com.joseph.insanegrouptestapp.data.mappers.CreateLeadDomainToCreateLeadDataMapper
import com.joseph.insanegrouptestapp.data.mappers.LanguageDomainToLanguageDataMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadDataToLeadDomainMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadDetailsCloudToLeadDetailsDataMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadDetailsDataToLeadDetailsDomainMapper
import com.joseph.insanegrouptestapp.data.mappers.LeadQueryToLeadDataMapper
import com.joseph.insanegrouptestapp.data.repository.LeadRepositoryImpl
import com.joseph.insanegrouptestapp.data.source.LeadCloudDataSource
import com.joseph.insanegrouptestapp.data.source.LeadCloudDataSourceImpl
import com.joseph.insanegrouptestapp.domain.repository.LeadRepository
import org.koin.dsl.module

val dataModule = module {
    factory<ApolloClientProvider> { ApolloClientProviderImpl() }
    factory<LeadCloudDataSource> {
        LeadCloudDataSourceImpl(
            apolloClientProvider = get(),
            createLeadDataToCreateLeadInputMapper = get(),
            leadQueryToLeadDataMapper = get(),
            leadDetailsCloudToLeadDetailsDataMapper = get()
        )
    }
    factory<LeadRepository> {
        LeadRepositoryImpl(
            leadCloudDataSource = get(),
            countryDataToCountryDomainMapper = get(),
            cityDataToCityDomainMapper = get(),
            languageDomainToLanguageDataMapper = get(),
            createLeadDomainToCreateLeadDataMapper = get(),
            leadDataToLeadDomainMapper = get(),
            leadDetailsDataToLeadDetailsDomainMapper = get(),
        )
    }
    factory { CountryDataToCountryDomainMapper() }
    factory { CityDataToCityDomainMapper() }
    factory { LanguageDomainToLanguageDataMapper() }
    factory { CreateLeadDomainToCreateLeadDataMapper() }
    factory { CreateLeadDataToCreateLeadInputMapper() }
    factory { LeadDataToLeadDomainMapper() }
    factory { LeadQueryToLeadDataMapper() }
    factory { LeadDetailsDataToLeadDetailsDomainMapper() }
    factory { LeadDetailsCloudToLeadDetailsDataMapper() }
}
