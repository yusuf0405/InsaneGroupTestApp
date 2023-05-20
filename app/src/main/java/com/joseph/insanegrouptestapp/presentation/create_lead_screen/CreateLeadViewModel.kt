package com.joseph.insanegrouptestapp.presentation.create_lead_screen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.domain.usecases.CreateNewLeadUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchCitiesUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchCountriesUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchLanguagesUseCase
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadIntentionTypes
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadSourcesUseCase
import com.joseph.insanegrouptestapp.presentation.mappers.CityDomainToCityUiMapper
import com.joseph.insanegrouptestapp.presentation.mappers.CountryDomainToCountryUiMapper
import com.joseph.insanegrouptestapp.presentation.mappers.CreateLeadUiToCreateLeadDomainMapper
import com.joseph.insanegrouptestapp.presentation.mappers.LanguageDomainToLanguageUiMapper
import com.joseph.insanegrouptestapp.presentation.models.City
import com.joseph.insanegrouptestapp.presentation.models.Country
import com.joseph.insanegrouptestapp.presentation.models.CreateLeadModel
import com.joseph.insanegrouptestapp.presentation.models.CreateLeadScreenClickType
import com.joseph.insanegrouptestapp.presentation.models.Language
import com.joseph.insanegrouptestapp.presentation.models.LeadIntentionType
import com.joseph.insanegrouptestapp.presentation.models.LeadSources
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModel
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModelOrientation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import okhttp3.internal.toImmutableList

data class CreateLeadScreenState(
    val loading: Boolean = false,
    val errorMessage: String = String(),
    val leadIntentionTypes: List<LeadIntentionType> = emptyList(),
    val countries: List<Country> = emptyList(),
    val languages: List<Language> = emptyList(),
    val cities: List<City> = emptyList(),
    val leadSources: List<LeadSources> = emptyList(),
    val selectedCountry: Country = Country.unknown,
    val selectedCity: City = City.unknown,
    val selectedLanguage: Language = Language.unknown,
    val selectedLeadSources: LeadSources = LeadSources.unknown,
    val selectedLeadIntentionType: LeadIntentionType = LeadIntentionType.unknown,
    val textFiledModels: List<TextFiledModel> = listOf(),
    val createLeadScreenClickType: CreateLeadScreenClickType = CreateLeadScreenClickType.NONE
)

class CreateLeadViewModel(
    private val fetchLeadIntentionTypes: FetchLeadIntentionTypes,
    private val fetchCountriesUseCase: FetchCountriesUseCase,
    private val fetchCitiesUseCase: FetchCitiesUseCase,
    private val fetchLanguagesUseCase: FetchLanguagesUseCase,
    private val fetchLeadSourcesUseCase: FetchLeadSourcesUseCase,
    private val createNewLeadUseCase: CreateNewLeadUseCase,
    private val countryDomainToCountryUiMapper: CountryDomainToCountryUiMapper,
    private val cityDomainToCityUiMapper: CityDomainToCityUiMapper,
    private val languageDomainToLanguageUiMapper: LanguageDomainToLanguageUiMapper,
    private val createLeadUiToCreateLeadDomainMapper: CreateLeadUiToCreateLeadDomainMapper
) : ViewModel() {

    var uiState by mutableStateOf(CreateLeadScreenState())

    private val firstNameFlow = MutableStateFlow(String())
    private val lastNameFlow = MutableStateFlow(String())
    private val emailFlow = MutableStateFlow(String())
    private val numberFlow = MutableStateFlow(String())

    private val firstNameTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)
    private val lastNameTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)
    private val emailTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)
    private val numberTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)

    private val selectedLanguageFlow = MutableStateFlow(Language.unknown)
    private val selectedTypeFlow = MutableStateFlow(LeadIntentionType.unknown)
    private val selectedCountryFlow = MutableStateFlow(Country.unknown)
    private val selectedCityFlow = MutableStateFlow(City.unknown)
    private val selectedSourceFlow = MutableStateFlow(LeadSources.unknown)

    private val languageTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)
    private val typeTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)
    private val countryTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)
    private val sourceTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)
    private val cityTextFiledStateFlow = MutableStateFlow(CreateLeadTextFiledState.DEFAULT)

    private val allCountries = MutableStateFlow(emptyList<Country>())
    private val allLanguages = MutableStateFlow(emptyList<Language>())

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                uiState = uiState.copy(loading = true)
                val leadIntentionTypes = fetchLeadIntentionTypes().map {
                    LeadIntentionType(id = it.id, title = it.title)
                }
                val leadSources = fetchLeadSourcesUseCase().map {
                    LeadSources(id = it.id, title = it.title)
                }
                val countries = fetchCountriesUseCase().map(countryDomainToCountryUiMapper::map)
                allCountries.tryEmit(countries)
                val languages = fetchLanguagesUseCase().map(languageDomainToLanguageUiMapper::map)
                allLanguages.tryEmit(languages)
                Triple(leadIntentionTypes, countries, Pair(leadSources, languages))
            }.onSuccess { result ->
                val (leadIntentionTypes, countries, leadSourcesAndLanguages) = result
                val (leadSources, languages) = leadSourcesAndLanguages
                uiState = uiState.copy(
                    loading = false,
                    leadIntentionTypes = leadIntentionTypes,
                    countries = countries,
                    languages = languages,
                    leadSources = leadSources,
                    textFiledModels = createTextFields()
                )
            }.onFailure { exception ->
                uiState = uiState.copy(
                    loading = false,
                    errorMessage = exception.localizedMessage ?: String()
                )
            }
        }
    }

    fun onDismissDialog() {
        uiState = uiState.copy(
            createLeadScreenClickType = CreateLeadScreenClickType.NONE
        )
    }

    fun setSelectLeadIntentionTypes(leadIntentionType: LeadIntentionType) {
        selectedTypeFlow.tryEmit(leadIntentionType)
        typeTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
    }

    fun setSelectCountry(country: Country) {
        fetchCountryCities(countryId = country.id)
        selectedCountryFlow.tryEmit(country)
        languageTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
        selectedCityFlow.tryEmit(City.unknown)
    }

    fun setSelectCity(city: City) {
        selectedCityFlow.tryEmit(city)
        cityTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
    }

    fun setSelectLanguage(language: Language) {
        selectedLanguageFlow.tryEmit(language)
        lastNameTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
    }

    fun setLeadSources(leadSources: LeadSources) {
        selectedSourceFlow.tryEmit(leadSources)
        sourceTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
    }

    fun onSearchCountry(query: String) {
        val countries = applyCountrySearchFiltered(items = uiState.countries, query = query)
        uiState = uiState.copy(countries = countries)
    }

    fun onSearchLanguage(query: String) {
        val languages = applyLanguageSearchFiltered(items = uiState.languages, query = query)
        uiState = uiState.copy(languages = languages)
    }

    private fun applyLanguageSearchFiltered(items: List<Language>, query: String) =
        if (query.isBlank()) allLanguages.value
        else items.filter { it.title.lowercase().contains(query.lowercase(), ignoreCase = true) }

    private fun applyCountrySearchFiltered(items: List<Country>, query: String) =
        if (query.isBlank()) allCountries.value
        else items.filter { it.title.lowercase().contains(query.lowercase(), ignoreCase = true) }

    private fun fetchCountryCities(countryId: Int) {
        viewModelScope.launch {
            val cities = fetchCitiesUseCase.invoke(countryId).map(cityDomainToCityUiMapper::map)
            uiState = uiState.copy(cities = cities)
        }
    }


    private fun createTextFields() = listOf(
        TextFiledModel.InputTextFiledModel(
            labelTextId = R.string.first_name,
            textFlow = firstNameFlow,
            onValueChangeListener = { firstName ->
                firstNameFlow.tryEmit(firstName)
                firstNameTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
            },
            isErrorFlow = firstNameTextFiledStateFlow,
            _orientation = TextFiledModelOrientation.VERTICAL
        ),

        TextFiledModel.InputTextFiledModel(
            labelTextId = R.string.last_name,
            secondaryDefaultTextId = R.string.last_name,
            textFlow = lastNameFlow,
            onValueChangeListener = { lastName ->
                lastNameFlow.tryEmit(lastName)
                lastNameTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
            },
            isErrorFlow = lastNameTextFiledStateFlow,
            _orientation = TextFiledModelOrientation.VERTICAL
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.city,
            secondaryDefaultTextId = R.string.city,
            textFlow = selectedCityFlow.map { it.title },
            onClickListener = {
                uiState = uiState.copy(
                    createLeadScreenClickType = CreateLeadScreenClickType.CITY
                )
            },
            isErrorFlow = cityTextFiledStateFlow,
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.lead_type,
            secondaryDefaultTextId = R.string.type,
            textFlow = selectedTypeFlow.map { it.title },
            onClickListener = {
                uiState = uiState.copy(
                    createLeadScreenClickType = CreateLeadScreenClickType.LEAD_TYPE
                )
            },
            isErrorFlow = typeTextFiledStateFlow,
            _orientation = TextFiledModelOrientation.HORIZONTAL
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.country,
            secondaryDefaultTextId = R.string.country,
            textFlow = selectedCountryFlow.map { it.title },
            onClickListener = {
                uiState = uiState.copy(
                    createLeadScreenClickType = CreateLeadScreenClickType.COUNTRY
                )
            },
            isErrorFlow = countryTextFiledStateFlow,
            _orientation = TextFiledModelOrientation.HORIZONTAL
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.language,
            secondaryDefaultTextId = R.string.language,
            textFlow = selectedLanguageFlow.map { it.title },
            onClickListener = {
                uiState = uiState.copy(
                    createLeadScreenClickType = CreateLeadScreenClickType.LANGUAGE
                )
            },
            isErrorFlow = languageTextFiledStateFlow,
        ),
        TextFiledModel.InputTextFiledModel(
            labelTextId = R.string.number,
            textFlow = numberFlow,
            onValueChangeListener = { number ->
                numberFlow.tryEmit(number)
                numberTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
            },
            isNumber = true,
            isErrorFlow = numberTextFiledStateFlow,
            countryCode = selectedCountryFlow.value.shortCode1
        ),
        TextFiledModel.InputTextFiledModel(
            labelTextId = R.string.email,
            textFlow = emailFlow,
            onValueChangeListener = { email ->
                emailFlow.tryEmit(email)
                emailTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.DEFAULT)
            },
            isErrorFlow = emailTextFiledStateFlow,
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.source,
            secondaryDefaultTextId = R.string.select_source,
            textFlow = selectedSourceFlow.map { it.title },
            onClickListener = {
                uiState = uiState.copy(
                    createLeadScreenClickType = CreateLeadScreenClickType.SOURCE
                )
            },
            isErrorFlow = sourceTextFiledStateFlow,
            _orientation = TextFiledModelOrientation.HORIZONTAL
        ),
    )

    fun onSaveButtonClick() {
        val firstName = firstNameFlow.value
        val lastName = lastNameFlow.value
        val number = numberFlow.value
        val email = emailFlow.value
        val selectedType = selectedTypeFlow.value
        val selectedCountry = selectedCountryFlow.value
        val selectedCity = selectedCityFlow.value
        val selectedLanguage = selectedLanguageFlow.value
        val selectedLeadSources = selectedSourceFlow.value

        if (selectedType == LeadIntentionType.unknown) {
            typeTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (selectedCountry == Country.unknown) {
            countryTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (selectedCity == City.unknown) {
            cityTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (selectedLanguage == Language.unknown) {
            languageTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (selectedLeadSources == LeadSources.unknown) {
            sourceTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (firstName.isBlank()) {
            firstNameTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (lastName.isBlank()) {
            lastNameTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (numberFlow.value.isBlank()) {
            numberTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        if (email.isBlank()) {
            emailTextFiledStateFlow.tryEmit(CreateLeadTextFiledState.ERROR)
        }
        val isNotCorrect = selectedType == LeadIntentionType.unknown
                || selectedCountry == Country.unknown
                || selectedCity == City.unknown
                || selectedLanguage == Language.unknown
                || selectedLeadSources == LeadSources.unknown
                || firstName.isBlank()
                || lastName.isBlank()
                || number.isBlank()
                || emailFlow.value.isBlank()

        if (isNotCorrect) return

        val createLeadModel = CreateLeadModel(
            firstName = firstName,
            lastName = lastName,
            leadSourceId = selectedLeadSources.id,
            leadTypeId = selectedType.id,
            countryId = selectedCountry.id,
            cityId = selectedCity.id,
            languageIds = listOf(selectedLanguage.id),
            number = number,
            email = email
        )

        viewModelScope.launch {
            createNewLeadUseCase(createLeadUiToCreateLeadDomainMapper.map(createLeadModel))
        }
    }
}