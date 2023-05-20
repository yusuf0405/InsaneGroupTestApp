package com.joseph.insanegrouptestapp.data.models

import java.util.Date

data class LeadDetailsData(
    val id: Int,
    val personId: Int,
    val avatarUrl: String,
    val displayName: String,
    val countryEmoji: String,
    val country: String,
    val nationality: String,
    val language: String,
    val propertyType: String,
    val city: String,
    val webSource: String,
    val leadIntentionTypeData: String,
    val adSource: String,
    val statusLegacyColor: String,
    val statusColor: String,
    val budget: Double,
    val statusStepCount: Int,
    val statusStep: Int,
    val leadSourcesData: String,
    val channelSource: String,
    val createdDate: Date,
    val updatedDate: Date,
    val birthDate: Date,
) {

    companion object {
        val unknown = LeadDetailsData(
            id = -1,
            personId = -1,
            statusStepCount = -1,
            statusStep = -1,
            avatarUrl = String(),
            displayName = String(),
            adSource = String(),
            nationality = String(),
            language = String(),
            propertyType = String(),
            countryEmoji = String(),
            country = String(),
            city = String(),
            webSource = String(),
            statusColor = String(),
            leadIntentionTypeData = String(),
            statusLegacyColor = String(),
            leadSourcesData = String(),
            channelSource = String(),
            createdDate = Date(),
            updatedDate = Date(),
            birthDate = Date(),
            budget = -1.0
        )
    }
}