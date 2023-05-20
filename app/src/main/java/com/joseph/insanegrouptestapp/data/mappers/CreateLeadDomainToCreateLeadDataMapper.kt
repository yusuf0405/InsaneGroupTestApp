package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.CreateLeadData
import com.joseph.insanegrouptestapp.domain.models.CreateLeadDomain

class CreateLeadDomainToCreateLeadDataMapper : Mapper<CreateLeadDomain, CreateLeadData> {

    override fun map(from: CreateLeadDomain): CreateLeadData = from.run {
        CreateLeadData(
            firstName = firstName,
            lastName = lastName,
            leadTypeId = leadTypeId,
            countryId = countryId,
            cityId = cityId,
            languageIds = languageIds,
            number = number,
            email = email,
            leadSourceId = leadSourceId
        )

    }
}