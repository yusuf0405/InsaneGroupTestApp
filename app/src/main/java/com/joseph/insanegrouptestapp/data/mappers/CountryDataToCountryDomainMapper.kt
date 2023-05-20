package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.CountryData
import com.joseph.insanegrouptestapp.domain.models.CountryDomain

class CountryDataToCountryDomainMapper : Mapper<CountryData, CountryDomain> {
    override fun map(from: CountryData): CountryDomain = from.run {
        CountryDomain(
            adWordsCode = adWordsCode,
            emoji = emoji,
            id = id,
            shortCode1 = shortCode1,
            shortCode2 = shortCode2,
            title = title
        )
    }
}