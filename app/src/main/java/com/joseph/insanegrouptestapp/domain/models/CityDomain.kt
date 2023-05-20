package com.joseph.insanegrouptestapp.domain.models

data class CityDomain(
    val id: Int,
    val countryId: Int,
    val title: String,
    val offset: String,
    val offsetMs: Int,
    val timezone: String,
)