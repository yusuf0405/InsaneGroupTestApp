package com.joseph.insanegrouptestapp.domain.models

import java.util.Date

data class LeadDomain(
    val id: Int,
    val personId: Int,
    val avatarUrl: String,
    val displayName: String,
    val countryEmoji: String,
    val leadIntentionTypeData: String,
    val leadStatus: String,
    val leadSourcesData: String,
    val channelSource: String,
    val createdDate: Date,
    val updatedDate: Date,
)