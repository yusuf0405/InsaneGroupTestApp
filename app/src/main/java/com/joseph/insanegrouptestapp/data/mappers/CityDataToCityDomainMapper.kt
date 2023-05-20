package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.CityData
import com.joseph.insanegrouptestapp.domain.models.CityDomain

class CityDataToCityDomainMapper : Mapper<CityData, CityDomain> {
    override fun map(from: CityData): CityDomain = from.run {
        CityDomain(
            id = id,
            countryId = countryId,
            title = title,
            offset = offset,
            offsetMs = offsetMs,
            timezone = timezone
        )

    }
}