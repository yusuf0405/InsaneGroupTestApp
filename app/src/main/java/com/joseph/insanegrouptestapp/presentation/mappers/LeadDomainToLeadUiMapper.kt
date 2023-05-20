package com.joseph.insanegrouptestapp.presentation.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.domain.models.LeadDomain
import com.joseph.insanegrouptestapp.presentation.models.LeadModel

class LeadDomainToLeadUiMapper : Mapper<LeadDomain, LeadModel> {

    override fun map(from: LeadDomain): LeadModel = from.run {
        LeadModel(
            id = id,
            personId = personId,
            avatarUrl = avatarUrl,
            displayName = displayName,
            countryEmoji = countryEmoji,
            leadIntentionType = leadIntentionTypeData,
            leadStatus = leadStatus,
            leadSources = leadSourcesData,
            channelSource = channelSource,
            createdDate = createdDate,
            updatedDate = updatedDate
        )
    }
}