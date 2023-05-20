package com.joseph.insanegrouptestapp.presentation.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LeadIntentionType(
    val id: Int,
    val title: String
) : Parcelable {
    companion object {

        val unknown = LeadIntentionType(
            id = -1,
            title = String()
        )
    }
}