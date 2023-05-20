package com.joseph.insanegrouptestapp.presentation.models

import java.util.Date

data class LeadDetailsUi(
    val id: Int,
    val personId: Int,
    val avatarUrl: String,
    val displayName: String,
    val countryEmoji: String,
    val country: String,
    val language: String,
    val city: String,
    val propertyType: String,
    val nationality: String,
    val webSource: String,
    val adSource: String,
    val leadIntentionType: String,
    val statusLegacyColor: String,
    val statusColor: String,
    val statusStepCount: Int,
    val statusStep: Int,
    val leadSourcesData: String,
    val channelSource: String,
    val createdDate: Date,
    val birthDate: Date,
    val updatedDate: Date,
    val budget: Double,
    val contactUiModels: List<ContactUiModel>,
)