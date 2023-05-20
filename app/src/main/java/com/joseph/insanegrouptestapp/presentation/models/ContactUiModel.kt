package com.joseph.insanegrouptestapp.presentation.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.joseph.insanegrouptestapp.R

enum class ContactUiModel(
    @StringRes val titleId: Int,
    @DrawableRes val iconId: Int,
    val enabled: Boolean
) {
    CALL(
        titleId = R.string.call,
        iconId = R.drawable.ic_calls,
        enabled = true
    ),
    MESSAGE(
        titleId = R.string.message,
        iconId = R.drawable.ic_message,
        enabled = true
    ),
    MAIL(
        titleId = R.string.mail,
        iconId = R.drawable.ic_mail,
        enabled = false
    ),
    FOLLOW_UP(
        titleId = R.string.follow_up,
        iconId = R.drawable.ic_follow_up,
        enabled = false
    ),
    MEETING(
        titleId = R.string.meeting,
        iconId = R.drawable.ic_meeting,
        enabled = false
    );

    companion object {

        fun fetchAllContactUiModel() = listOf(
            CALL,
            MESSAGE,
            MAIL,
            FOLLOW_UP,
            MEETING
        )
    }
}