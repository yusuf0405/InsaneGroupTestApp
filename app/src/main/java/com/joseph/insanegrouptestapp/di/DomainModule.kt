package com.joseph.insanegrouptestapp.di

import com.joseph.insanegrouptestapp.domain.usecases.CreateNewLeadUseCase
import com.joseph.insanegrouptestapp.domain.usecases.CreateNewLeadUseCaseImpl
import com.joseph.insanegrouptestapp.domain.usecases.FetchAllLeadsUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchAllLeadsUseCaseImpl
import com.joseph.insanegrouptestapp.domain.usecases.FetchCitiesUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchCitiesUseCaseImpl
import com.joseph.insanegrouptestapp.domain.usecases.FetchCountriesUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchCountriesUseCaseImpl
import com.joseph.insanegrouptestapp.domain.usecases.FetchLanguagesUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchLanguagesUseCaseImpl
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadByIdUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadByIdUseCaseImpl
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadIntentionTypes
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadIntentionTypesImpl
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadSourcesUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadSourcesUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<FetchLeadIntentionTypes> { FetchLeadIntentionTypesImpl(repository = get()) }
    factory<FetchCountriesUseCase> { FetchCountriesUseCaseImpl(repository = get()) }
    factory<FetchCitiesUseCase> { FetchCitiesUseCaseImpl(repository = get()) }
    factory<FetchLanguagesUseCase> { FetchLanguagesUseCaseImpl(repository = get()) }
    factory<FetchLeadSourcesUseCase> { FetchLeadSourcesUseCaseImpl(repository = get()) }
    factory<CreateNewLeadUseCase> { CreateNewLeadUseCaseImpl(repository = get()) }
    factory<FetchAllLeadsUseCase> { FetchAllLeadsUseCaseImpl(repository = get()) }
    factory<FetchLeadByIdUseCase> { FetchLeadByIdUseCaseImpl(repository = get()) }
}
