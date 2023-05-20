package com.joseph.insanegrouptestapp.data.models

data class LeadIntentionTypeData(
    val id: Int,
    val title: String
) {
    companion object {
        val unknown = LeadIntentionTypeData(
            id = 0,
            title = String()
        )
    }
}