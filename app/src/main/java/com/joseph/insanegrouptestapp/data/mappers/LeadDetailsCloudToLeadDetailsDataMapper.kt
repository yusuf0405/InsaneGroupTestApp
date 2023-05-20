package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.LeadQuery
import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.LeadDetailsData
import java.util.Date

class LeadDetailsCloudToLeadDetailsDataMapper : Mapper<LeadQuery.Data1?, LeadDetailsData> {

    override fun map(from: LeadQuery.Data1?): LeadDetailsData = from.run {
        if (this == null) return@run LeadDetailsData.unknown
        LeadDetailsData(
            id = id,
            personId = personId,
            avatarUrl = avatar?.path ?: String(),
            displayName = displayName ?: String(),
            countryEmoji = country?.emoji ?: String(),
            leadIntentionTypeData = intention?.title ?: String(),
            country = country?.title ?: String(),
            webSource = webSource?.title ?: String(),
            city = city?.title ?: String(),
            language = languages?.firstOrNull()?.title ?: String(),
            propertyType = propertyType?.title ?: String(),
            nationality = nationality?.title ?: String(),
            adSource = adSource?.title ?: String(),
            statusLegacyColor = status?.legacyColor ?: String(),
            statusStepCount = status?.stepsCount ?: -1,
            statusStep = status?.step ?: -1,
            statusColor = status?.color ?: String(),
            leadSourcesData = source?.title ?: String(),
            channelSource = channelSource?.title ?: String(),
            createdDate = createdAt ?: Date(0, 0, 0),
            updatedDate = updatedAt ?: Date(0, 0, 0),
            birthDate = updatedAt ?: Date(0, 0, 0),
            budget = budget ?: 1.0
        )
    }

}