package com.joseph.insanegrouptestapp.presentation.models

data class Country(
    val adWordsCode: Int,
    val emoji: String,
    val id: Int,
    val shortCode1: String,
    val shortCode2: String,
    val title: String
) {

    fun emojiAndTitle() = "$emoji $title"

    companion object {

        val unknown = Country(
            adWordsCode = -1,
            emoji = String(),
            id = -1,
            shortCode2 = String(),
            shortCode1 = String(),
            title = String()
        )
    }
}