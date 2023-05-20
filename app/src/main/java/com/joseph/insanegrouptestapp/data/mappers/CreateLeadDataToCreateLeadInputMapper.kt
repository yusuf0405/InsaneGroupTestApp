package com.joseph.insanegrouptestapp.data.mappers

import com.apollographql.apollo3.api.Optional
import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.CreateLeadData
import com.joseph.type.ContactDataInput
import com.joseph.type.CreateLeadInput

class CreateLeadDataToCreateLeadInputMapper : Mapper<CreateLeadData, CreateLeadInput> {

    override fun map(from: CreateLeadData): CreateLeadInput = from.run {
        CreateLeadInput(
            firstName = firstName,
            lastName = Optional.present(lastName),
            countryId = Optional.present(countryId),
            cityId = Optional.present(cityId),
            languageIds = languageIds,
            contacts = listOf(
                ContactDataInput(
                    email = Optional.present(email),
                    phone = Optional.present(number),
                )
            ),
            leadSourceId = Optional.present(leadSourceId),
            intentionId = leadTypeId,
        )

    }
}