package com.joseph.insanegrouptestapp.presentation.models

data class Language(
    val id: Int,
    val title: String,
    val shortCode: String,
) {
    companion object {
        val unknown = Language(
            id = -1,
            title = String(),
            shortCode = String()
        )
    }
}