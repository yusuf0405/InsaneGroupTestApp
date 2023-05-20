package com.joseph.insanegrouptestapp.data.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.data.models.LanguageData
import com.joseph.insanegrouptestapp.domain.models.LanguageDomain

class LanguageDomainToLanguageDataMapper : Mapper<LanguageData, LanguageDomain> {

    override fun map(from: LanguageData): LanguageDomain = from.run {
        LanguageDomain(
            id = id,
            title = title,
            shortCode = shortCode
        )
    }
}