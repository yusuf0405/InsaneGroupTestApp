package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.LeadData
import com.joseph.insanegrouptestapp.domain.models.LeadDomain

class LeadDataToLeadDomainMapper : Mapper<LeadData, LeadDomain> {

    override fun map(from: LeadData): LeadDomain = from.run {
        LeadDomain(
            id = id,
            personId = personId,
            avatarUrl = avatarUrl,
            displayName = displayName,
            countryEmoji = countryEmoji,
            leadIntentionTypeData = leadIntentionTypeData,
            leadStatus = leadStatus,
            leadSourcesData = leadSourcesData,
            channelSource = channelSource,
            createdDate = createdDate,
            updatedDate = updatedDate
        )
    }
}