package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.LeadsQuery
import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.LeadData
import java.util.Date

class LeadQueryToLeadDataMapper : Mapper<LeadsQuery.Data1, LeadData> {

    override fun map(from: LeadsQuery.Data1): LeadData = from.run {
        LeadData(
            id = id,
            personId = personId,
            avatarUrl = avatar?.path ?: String(),
            displayName = displayName ?: String(),
            countryEmoji = country?.emoji ?: String(),
            leadIntentionTypeData = intention?.title ?: String(),
            leadStatus = status?.title ?: String(),
            leadSourcesData = source?.title ?: String(),
            channelSource = channelSource?.title ?: String(),
            createdDate = createdAt ?: Date(0, 0, 0),
            updatedDate = updatedAt ?: Date(0, 0, 0)
        )
    }
}