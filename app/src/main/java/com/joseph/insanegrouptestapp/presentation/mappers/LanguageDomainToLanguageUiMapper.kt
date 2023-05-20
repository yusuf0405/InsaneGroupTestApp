package com.joseph.insanegrouptestapp.presentation.mappers

import com.joseph.insanegrouptestapp.common.Mapper
import com.joseph.insanegrouptestapp.domain.models.LanguageDomain
import com.joseph.insanegrouptestapp.presentation.models.Language

class LanguageDomainToLanguageUiMapper : Mapper<LanguageDomain, Language> {

    override fun map(from: LanguageDomain): Language = from.run {
        Language(
            id = id,
            title = title,
            shortCode = shortCode
        )
    }
}