package com.joseph.insanegrouptestapp.presentation.mappers

import android.content.Context
import androidx.compose.ui.res.stringResource
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.domain.models.LeadDetailsDomain
import com.joseph.insanegrouptestapp.presentation.models.ContactUiModel
import com.joseph.insanegrouptestapp.presentation.models.LeadDetailsUi
import com.joseph.insanegrouptestapp.presentation.models.LeadModel
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModel
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModelOrientation
import java.text.SimpleDateFormat
import java.util.Date

class LeadDetailsDomainToLeadDetailsUiMapper(
    private val context: Context
) : Mapper<LeadDetailsDomain, LeadDetailsUi> {

    override fun map(from: LeadDetailsDomain): LeadDetailsUi = from.run {
        LeadDetailsUi(
            id = id,
            personId = personId,
            avatarUrl = avatarUrl,
            displayName = displayName,
            countryEmoji = countryEmoji,
            country = country,
            propertyType = propertyType,
            city = city,
            nationality = nationality,
            language = language,
            webSource = webSource,
            adSource = adSource,
            leadIntentionType = leadIntentionType,
            statusLegacyColor = statusLegacyColor,
            statusColor = statusColor,
            statusStepCount = statusStepCount,
            statusStep = statusStep,
            leadSourcesData = leadSourcesData,
            channelSource = channelSource,
            createdDate = createdDate,
            updatedDate = updatedDate,
            birthDate = birthDate,
            budget = budget,
            contactUiModels = ContactUiModel.fetchAllContactUiModel(),
        )
    }
}