package com.joseph.insanegrouptestapp.presentation.create_lead_screen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.common.CustomTextFiled
import com.joseph.insanegrouptestapp.presentation.common.LeadAppBar
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs.SelectCityBottomSheet
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs.SelectCountriesBottomSheet
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs.SelectLanguageBottomSheet
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs.SelectLeadIntentionTypesBottomSheet
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs.SelectLeadSourcesBottomSheet
import com.joseph.insanegrouptestapp.presentation.models.City
import com.joseph.insanegrouptestapp.presentation.models.Country
import com.joseph.insanegrouptestapp.presentation.models.CreateLeadScreenClickType
import com.joseph.insanegrouptestapp.presentation.models.Language
import com.joseph.insanegrouptestapp.presentation.models.LeadIntentionType
import com.joseph.insanegrouptestapp.presentation.models.LeadSources
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModelOrientation

@SuppressLint("CoroutineCreationDuringComposition", "FlowOperatorInvokedInComposition")
@Composable
fun CreateLeadScreen(
    uiState: CreateLeadScreenState,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onSelectedLeadIntentionType: (LeadIntentionType) -> Unit,
    onSelectedCountry: (Country) -> Unit,
    onSelectedCity: (City) -> Unit,
    onSelectedLanguage: (Language) -> Unit,
    onSelectedLeadSources: (LeadSources) -> Unit,
    onSaveButtonClick: () -> Unit,
    onSearchCountry: (String) -> Unit,
    onSearchLanguage: (String) -> Unit,
    onDismissDialog: () -> Unit,
) {
    val citySelectFieldVisibility = rememberSaveable { (mutableStateOf(false)) }
    citySelectFieldVisibility.value = uiState.cities.isNotEmpty()

    val openSelectLeadIntentionTypesBottomSheet = remember { mutableStateOf(false) }
    val openSelectCountriesBottomSheet = remember { mutableStateOf(false) }
    val openSelectCitiesBottomSheet = remember { mutableStateOf(false) }
    val openSelectLanguagesBottomSheet = remember { mutableStateOf(false) }
    val openSelectLeadSourcesBottomSheet = remember { mutableStateOf(false) }

    val selectedLeadIntentionState = rememberSaveable { mutableStateOf(LeadIntentionType.unknown) }
    selectedLeadIntentionState.value = uiState.selectedLeadIntentionType

    val clickType = remember { mutableStateOf(uiState.createLeadScreenClickType) }
    clickType.value = uiState.createLeadScreenClickType

    openSelectLeadIntentionTypesBottomSheet.value =
        clickType.value == CreateLeadScreenClickType.LEAD_TYPE
    openSelectLanguagesBottomSheet.value =
        clickType.value == CreateLeadScreenClickType.LANGUAGE
    openSelectLeadSourcesBottomSheet.value =
        clickType.value == CreateLeadScreenClickType.SOURCE
    openSelectCountriesBottomSheet.value =
        clickType.value == CreateLeadScreenClickType.COUNTRY
    openSelectCitiesBottomSheet.value =
        clickType.value == CreateLeadScreenClickType.CITY

    if (openSelectLeadIntentionTypesBottomSheet.value) {
        SelectLeadIntentionTypesBottomSheet(
            onDismiss = onDismissDialog,
            leadIntentionTypes = uiState.leadIntentionTypes,
            currentSelectedLeadIntentionType = uiState.selectedLeadIntentionType,
            onSelectedLeadIntention = {
                onSelectedLeadIntentionType(it)
                onDismissDialog()
            }
        )
    }
    if (openSelectCountriesBottomSheet.value) {
        SelectCountriesBottomSheet(
            onDismiss = {
                onSearchCountry(String())
                onDismissDialog()
            },
            countries = uiState.countries,
            onSelectedCountry = {
                onSelectedCountry(it)
                onSearchCountry(String())
                onDismissDialog()
            },
            onSearch = onSearchCountry,
        )

    }
    if (openSelectCitiesBottomSheet.value && uiState.cities.isNotEmpty()) {
        SelectCityBottomSheet(
            cities = uiState.cities,
            onSelectedCity = {
                onSelectedCity(it)
                onDismissDialog()
            },
            onDismiss = onDismissDialog
        )
    }

    if (openSelectLanguagesBottomSheet.value) {
        SelectLanguageBottomSheet(
            languages = uiState.languages,
            selectedLanguage = uiState.selectedLanguage,
            onSelectedLanguage = {
                onSelectedLanguage(it)
                onDismissDialog()
            },
            onDismiss = onDismissDialog,
            onSearch = onSearchLanguage
        )
    }
    if (openSelectLeadSourcesBottomSheet.value) {
        SelectLeadSourcesBottomSheet(
            onDismiss = onDismissDialog,
            leadSources = uiState.leadSources,
            onSelectedLeadSources = {
                onSelectedLeadSources(it)
                onDismissDialog()
            }
        )
    }


    if (uiState.loading) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    if (uiState.leadIntentionTypes.isNotEmpty()
        || uiState.countries.isNotEmpty()
        || uiState.languages.isNotEmpty()
        || uiState.leadSources.isNotEmpty()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LeadAppBar(
                onNavigateBack = onNavigateBack,
                title = stringResource(id = R.string.lead_information)
            )
            LazyVerticalGrid(
                modifier = modifier.fillMaxWidth(),
                columns = GridCells.Fixed(2),
            ) {
                val verticalTextFields = uiState.textFiledModels.filter {
                    it.orientation == TextFiledModelOrientation.VERTICAL
                }
                val horizontalTextFields = uiState.textFiledModels.filter {
                    it.orientation == TextFiledModelOrientation.HORIZONTAL
                }
                items(
                    count = verticalTextFields.size,
                ) { index ->
                    CustomTextFiled(
                        modifier = modifier.padding(all = 5.dp),
                        textFiledModel = verticalTextFields[index],
                    )
                }
                items(
                    count = horizontalTextFields.size,
                    span = { GridItemSpan(2) }
                ) { index ->
                    CustomTextFiled(
                        modifier = modifier.padding(all = 5.dp),
                        textFiledModel = horizontalTextFields[index],
                    )
                }

            }
            Spacer(modifier = modifier.weight(1f))
            CreateLeadButtonsBlock(
                onNavigateBack = onNavigateBack,
                onSaveButtonClick = onSaveButtonClick
            )
        }
    }
}

@Composable
private fun CreateLeadButtonsBlock(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onSaveButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 16.dp,
                horizontal = 10.dp,
            )
    ) {
        OutlinedButton(
            modifier = modifier
                .height(40.dp)
                .weight(1f)
                .padding(end = 8.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White
            ),
            border = BorderStroke(
                width = 1.dp,
                color = colorResource(id = R.color.purple)
            ),
            onClick = {
                onNavigateBack()
            },
            shape = CircleShape.copy(all = CornerSize(16))
        ) {
            Text(
                text = stringResource(id = R.string.cancel),
                color = colorResource(id = R.color.purple),
            )
        }
        Button(
            modifier = modifier
                .height(40.dp)
                .weight(1f)
                .padding(start = 8.dp),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = 4.dp
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.purple)
            ),
            onClick = {
                onSaveButtonClick()
            },
            shape = CircleShape.copy(all = CornerSize(16))
        ) {
            Text(
                text = stringResource(id = R.string.save),
                color = Color.White,
            )
        }
    }
}