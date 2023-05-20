package com.joseph.insanegrouptestapp.presentation.models

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

data class LeadModel(
    val id: Int,
    val personId: Int,
    val avatarUrl: String,
    val displayName: String,
    val countryEmoji: String,
    val leadIntentionType: String,
    val leadStatus: String,
    val leadSources: String,
    val channelSource: String,
    val createdDate: Date,
    val updatedDate: Date,
) {
    private companion object {
        const val PATTERN = "dd.MM.yyyy"
    }

    fun displayNameAndEmoji() = "$displayName $countryEmoji"

    @SuppressLint("SimpleDateFormat")
    fun getFormattedCreatedDate(): String {
        val fmt = SimpleDateFormat(PATTERN)
        return fmt.format(createdDate)
    }

    @SuppressLint("SimpleDateFormat")
    fun getFormattedUpdatedDate(): String {
        val fmt = SimpleDateFormat(PATTERN)
        return fmt.format(createdDate)
    }
}
