package com.joseph.insanegrouptestapp.presentation.models

data class City(
    val id: Int,
    val countryId: Int,
    val title: String,
    val offset: String,
    val offsetMs: Int,
    val timezone: String,
) {

    companion object {

        val unknown = City(
            id = -1,
            countryId = -1,
            title = String(),
            offset = String(),
            offsetMs = -1,
            timezone = String()
        )
    }
}