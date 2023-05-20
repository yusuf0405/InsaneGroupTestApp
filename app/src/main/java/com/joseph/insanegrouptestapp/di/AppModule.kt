package com.joseph.insanegrouptestapp.di

import com.joseph.insanegrouptestapp.presentation.create_lead_screen.CreateLeadViewModel
import com.joseph.insanegrouptestapp.presentation.lead_profile.LeadProfileScreenViewModel
import com.joseph.insanegrouptestapp.presentation.leads_screen.LeadsScreenViewModel
import com.joseph.insanegrouptestapp.presentation.mappers.CityDomainToCityUiMapper
import com.joseph.insanegrouptestapp.presentation.mappers.CountryDomainToCountryUiMapper
import com.joseph.insanegrouptestapp.presentation.mappers.CreateLeadUiToCreateLeadDomainMapper
import com.joseph.insanegrouptestapp.presentation.mappers.LanguageDomainToLanguageUiMapper
import com.joseph.insanegrouptestapp.presentation.mappers.LeadDetailsDomainToLeadDetailsUiMapper
import com.joseph.insanegrouptestapp.presentation.mappers.LeadDomainToLeadUiMapper
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        LeadsScreenViewModel(
            fetchAllLeadsUseCase = get(),
            leadDomainToLeadUiMapper = get(),
        )
    }
    viewModel { params ->
        LeadProfileScreenViewModel(
            leadId = params.get(),
            fetchLeadByIdUseCase = get(),
            leadDetailsDomainToLeadDetailsUiMapper = get(),
        )
    }

    viewModel {
        CreateLeadViewModel(
            fetchLeadIntentionTypes = get(),
            fetchCountriesUseCase = get(),
            fetchCitiesUseCase = get(),
            fetchLeadSourcesUseCase = get(),
            fetchLanguagesUseCase = get(),
            createNewLeadUseCase = get(),
            countryDomainToCountryUiMapper = get(),
            cityDomainToCityUiMapper = get(),
            languageDomainToLanguageUiMapper = get(),
            createLeadUiToCreateLeadDomainMapper = get(),
        )
    }

    factory { CountryDomainToCountryUiMapper() }
    factory { CityDomainToCityUiMapper() }
    factory { LanguageDomainToLanguageUiMapper() }
    factory { CreateLeadUiToCreateLeadDomainMapper() }
    factory { LeadDomainToLeadUiMapper() }
    factory { LeadDetailsDomainToLeadDetailsUiMapper(context = androidContext()) }
}