package com.joseph.insanegrouptestapp.presentation.models

data class LeadSources(
    val id: Int,
    val title: String
) {
    companion object {

        val unknown = LeadSources(
            id = -1,
            title = String()
        )
    }
}