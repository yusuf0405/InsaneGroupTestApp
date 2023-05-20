package com.joseph.insanegrouptestapp.presentation.models

import androidx.annotation.StringRes
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.CreateLeadTextFiledState
import kotlinx.coroutines.flow.Flow
import java.util.Locale

enum class TextFiledModelOrientation {
    VERTICAL,
    HORIZONTAL
}

sealed class TextFiledModel(
    var orientation: TextFiledModelOrientation,
    val textFieldId: Int
) {

    class SelectTextFiledModel(
        @StringRes val labelTextId: Int,
        val textFlow: Flow<String>,
        var onClickListener: () -> Unit = { },
        val _orientation: TextFiledModelOrientation = TextFiledModelOrientation.VERTICAL,
        @StringRes val secondaryDefaultTextId: Int = R.string.unknown,
        val isErrorFlow: Flow<CreateLeadTextFiledState>,
    ) : TextFiledModel(
        orientation = _orientation,
        textFieldId = labelTextId
    )

    class InputTextFiledModel(
        @StringRes val labelTextId: Int,
        val textFlow: Flow<String>,
        val isNumber: Boolean = false,
        val countryCode: String = Locale.getDefault().country,
        var onValueChangeListener: ((String) -> Unit)? = null,
        @StringRes val secondaryDefaultTextId: Int = R.string.unknown,
        val isErrorFlow: Flow<CreateLeadTextFiledState>,
        val _orientation: TextFiledModelOrientation = TextFiledModelOrientation.HORIZONTAL
    ) : TextFiledModel(
        orientation = _orientation,
        textFieldId = labelTextId
    )

    fun setOrientation(orientation: TextFiledModelOrientation) = this.apply {
        this.orientation = orientation
    }

}