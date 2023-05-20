package com.joseph.insanegrouptestapp.presentation.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.domain.models.CityDomain
import com.joseph.insanegrouptestapp.presentation.models.City

class CityDomainToCityUiMapper : Mapper<CityDomain, City> {
    override fun map(from: CityDomain): City = from.run {
        City(
            id = id,
            countryId = countryId,
            title = title,
            offset = offset,
            offsetMs = offsetMs,
            timezone = timezone
        )

    }
}