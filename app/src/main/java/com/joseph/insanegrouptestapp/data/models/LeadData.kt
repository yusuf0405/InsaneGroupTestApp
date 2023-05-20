package com.joseph.insanegrouptestapp.data.models

import java.util.Date

data class LeadData(
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