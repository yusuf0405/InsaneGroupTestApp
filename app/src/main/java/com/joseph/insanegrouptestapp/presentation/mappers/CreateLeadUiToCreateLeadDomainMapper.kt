package com.joseph.insanegrouptestapp.presentation.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.domain.models.CreateLeadDomain
import com.joseph.insanegrouptestapp.presentation.models.CreateLeadModel

class CreateLeadUiToCreateLeadDomainMapper : Mapper<CreateLeadModel, CreateLeadDomain> {

    override fun map(from: CreateLeadModel): CreateLeadDomain = from.run {
        CreateLeadDomain(
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