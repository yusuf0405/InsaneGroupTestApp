package com.joseph.insanegrouptestapp.data.models

data class CityData(
    val id: Int,
    val countryId: Int,
    val title: String,
    val offset: String,
    val offsetMs: Int,
    val timezone: String,
)