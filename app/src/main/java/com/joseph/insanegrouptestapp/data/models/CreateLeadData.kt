package com.joseph.insanegrouptestapp.data.models

data class CreateLeadData(
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
