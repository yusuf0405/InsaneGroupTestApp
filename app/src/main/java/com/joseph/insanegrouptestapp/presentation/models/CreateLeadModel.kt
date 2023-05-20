package com.joseph.insanegrouptestapp.presentation.models

data class CreateLeadModel(
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
