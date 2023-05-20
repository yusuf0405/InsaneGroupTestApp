package com.joseph.insanegrouptestapp.domain.models

data class CreateLeadDomain(
    val firstName: String,
    val lastName: String,
    val leadTypeId: Int,
    val countryId: Int,
    val cityId: Int,
    val languageIds: List<Int>,
    val number: String,
    val email: String,
    val leadSourceId: Int
)
