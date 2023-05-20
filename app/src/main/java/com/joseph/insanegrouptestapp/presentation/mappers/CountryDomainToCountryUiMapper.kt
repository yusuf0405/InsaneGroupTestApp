package com.joseph.insanegrouptestapp.presentation.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.CountryData
import com.joseph.insanegrouptestapp.domain.models.CountryDomain
import com.joseph.insanegrouptestapp.presentation.models.Country

class CountryDomainToCountryUiMapper : Mapper<CountryDomain, Country> {
    override fun map(from: CountryDomain): Country = from.run {
        Country(
            adWordsCode = adWordsCode,
            emoji = emoji,
            id = id,
            shortCode1 = shortCode1,
            shortCode2 = shortCode2,
            title = title
        )
    }
}