package com.joseph.insanegrouptestapp.presentation.lead_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.domain.usecases.FetchLeadByIdUseCase
import com.joseph.insanegrouptestapp.presentation.mappers.LeadDetailsDomainToLeadDetailsUiMapper
import com.joseph.insanegrouptestapp.presentation.models.LeadDetailsUi
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModel
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModelOrientation
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date

data class LeadProfileScreenState(
    val loading: Boolean = false,
    val lead: LeadDetailsUi? = null,
    val errorMessage: String = String(),
    val textFiledModels: List<TextFiledModel> = listOf(),
)

class LeadProfileScreenViewModel(
    private val leadId: Int,
    private val fetchLeadByIdUseCase: FetchLeadByIdUseCase,
    private val leadDetailsDomainToLeadDetailsUiMapper: LeadDetailsDomainToLeadDetailsUiMapper
) : ViewModel() {

    var uiState by mutableStateOf(LeadProfileScreenState())

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                uiState = uiState.copy(loading = true)
                val leadDomain = fetchLeadByIdUseCase(leadId = leadId)
                leadDetailsDomainToLeadDetailsUiMapper.map(leadDomain)
            }.onSuccess { lead ->
                uiState = uiState.copy(
                    loading = false,
                    lead = lead,
                    textFiledModels = createTextFiledModels(lead)
                )

            }.onFailure {
                uiState = uiState.copy(
                    loading = false,
                    errorMessage = it.localizedMessage ?: String()
                )
            }
        }
    }

    private fun createTextFiledModels(
        lead: LeadDetailsUi
    ) = listOf<TextFiledModel>(
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.lead_intention,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.ad_source,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.country,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.web_source,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.city_and_region,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),

        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.channel_source,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),

        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.language,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),

        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.property_type,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),

        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.nationality,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.language,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf()
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.budget,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf(),
            secondaryDefaultTextId = R.string.from_0_to_0,
            _orientation = TextFiledModelOrientation.HORIZONTAL
        ),
        TextFiledModel.SelectTextFiledModel(
            labelTextId = R.string.location,
            textFlow = flowOf(lead.leadIntentionType),
            isErrorFlow = flowOf(),
            secondaryDefaultTextId = R.string.dubai_creek_harbour_the_lagoons,
            _orientation = TextFiledModelOrientation.HORIZONTAL
        )
    )

    private fun createBirthDate(birthDate: Date): String {
        val pattern = "dd.MM.yyyy"
        val fmt = SimpleDateFormat(pattern)
        return fmt.format(birthDate)
    }
}