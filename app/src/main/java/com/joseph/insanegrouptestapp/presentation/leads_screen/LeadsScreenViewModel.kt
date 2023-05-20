package com.joseph.insanegrouptestapp.presentation.leads_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joseph.insanegrouptestapp.domain.usecases.FetchAllLeadsUseCase
import com.joseph.insanegrouptestapp.presentation.mappers.LeadDomainToLeadUiMapper
import com.joseph.insanegrouptestapp.presentation.models.LeadModel
import kotlinx.coroutines.launch

data class LeadsScreenState(
    val loading: Boolean = false,
    val leads: List<LeadModel> = emptyList(),
    val errorMassage: String = String()
)

class LeadsScreenViewModel(
    private val fetchAllLeadsUseCase: FetchAllLeadsUseCase,
    private val leadDomainToLeadUiMapper: LeadDomainToLeadUiMapper
) : ViewModel() {

    var uiState by mutableStateOf(LeadsScreenState())

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                uiState = uiState.copy(loading = true)
                fetchAllLeadsUseCase().map(leadDomainToLeadUiMapper::map)
            }.onSuccess { leads ->
                uiState = uiState.copy(
                    leads = leads,
                    loading = false
                )
            }.onFailure {
                uiState = uiState.copy(
                    errorMassage = it.localizedMessage ?: String(),
                    loading = false
                )

            }
        }
    }


}