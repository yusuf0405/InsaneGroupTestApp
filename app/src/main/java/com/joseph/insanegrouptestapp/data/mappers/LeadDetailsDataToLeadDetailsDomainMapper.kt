package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.LeadDetailsData
import com.joseph.insanegrouptestapp.domain.models.LeadDetailsDomain

class LeadDetailsDataToLeadDetailsDomainMapper : Mapper<LeadDetailsData, LeadDetailsDomain> {

    override fun map(from: LeadDetailsData): LeadDetailsDomain = from.run {
        LeadDetailsDomain(
            id = id,
            personId = personId,
            avatarUrl = avatarUrl,
            displayName = displayName,
            countryEmoji = countryEmoji,
            leadIntentionType = leadIntentionTypeData,
            statusLegacyColor = statusLegacyColor,
            statusStepCount = statusStepCount,
            statusStep = statusStep,
            leadSourcesData = leadSourcesData,
            channelSource = channelSource,
            createdDate = createdDate,
            updatedDate = updatedDate,
            adSource = adSource,
            country = country,
            webSource = webSource,
            city = city,
            language = language,
            propertyType = propertyType,
            nationality = nationality,
            birthDate = birthDate,
            statusColor = statusColor,
            budget = budget
        )
    }
}