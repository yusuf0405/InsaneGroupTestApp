package com.joseph.insanegrouptestapp.domain.models

import java.util.Date

data class LeadDetailsDomain(
    val id: Int,
    val personId: Int,
    val avatarUrl: String,
    val displayName: String,
    val countryEmoji: String,
    val country: String,
    val nationality: String,
    val propertyType: String,
    val language: String,
    val city: String,
    val webSource: String,
    val leadIntentionType: String,
    val adSource: String,
    val statusLegacyColor: String,
    val statusColor: String,
    val statusStepCount: Int,
    val statusStep: Int,
    val budget: Double,
    val leadSourcesData: String,
    val channelSource: String,
    val createdDate: Date,
    val updatedDate: Date,
    val birthDate: Date,
)