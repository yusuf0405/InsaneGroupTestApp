package com.joseph.insanegrouptestapp.presentation.common

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.common.utils.PhoneNumberVisualTransformation
import com.joseph.insanegrouptestapp.presentation.create_lead_screen.CreateLeadTextFiledState
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Locale

@Composable
fun CustomTextFiled(
    modifier: Modifier = Modifier,
    textFiledModel: TextFiledModel,
) {
    when (textFiledModel) {
        is TextFiledModel.SelectTextFiledModel -> {
            SelectTextFiled(
                modifier = modifier,
                textFiledModel = textFiledModel,
            )
        }

        is TextFiledModel.InputTextFiledModel -> {
            InputTextFiled(
                modifier = modifier,
                textFiledModel = textFiledModel
            )
        }
    }

}

@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SelectTextFiled(
    modifier: Modifier = Modifier,
    textFiledModel: TextFiledModel.SelectTextFiledModel,
) {
    val scope = rememberCoroutineScope()
    val unknown = stringResource(id = R.string.unknown)
    val text = rememberSaveable { mutableStateOf(unknown) }
    val isError = rememberSaveable { mutableStateOf(false) }

    textFiledModel.textFlow.onEach {
        text.value = it
    }.launchIn(scope)

    textFiledModel.isErrorFlow.onEach {
        isError.value = it == CreateLeadTextFiledState.ERROR
    }.launchIn(scope)

    val color = if (text.value == unknown || text.value == stringResource(
            id = textFiledModel.secondaryDefaultTextId
        )
    ) colorResource(id = R.color.label_text_color)

    else MaterialTheme.colorScheme.onSurface
    OutlinedTextField(
        modifier = modifier
            .clickable { textFiledModel.onClickListener() },
        value = text.value,
        isError = isError.value,
        interactionSource = remember { MutableInteractionSource() }
            .also { interactionSource ->
                LaunchedEffect(interactionSource) {
                    interactionSource.interactions.collect {
                        if (it is PressInteraction.Release) {
                            textFiledModel.onClickListener()
                        }
                    }
                }
            },
        singleLine = true,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_select),
                contentDescription = null,
                tint = colorResource(id = R.color.light_gray)
            )

        },
        readOnly = true,
        shape = CircleShape.copy(all = CornerSize(16)),
        onValueChange = {
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledSupportingTextColor = color,
            containerColor = MaterialTheme.colorScheme.background,
            cursorColor = color,
            disabledLabelColor = Color.Blue,
            unfocusedBorderColor = color,
            unfocusedLabelColor = color,
            unfocusedTextColor = color
        ),
        label = {
            Text(
                text = stringResource(id = textFiledModel.labelTextId),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            )
        }
    )
}

@SuppressLint("FlowOperatorInvokedInComposition", "CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputTextFiled(
    modifier: Modifier = Modifier,
    textFiledModel: TextFiledModel.InputTextFiledModel,
) {
    val scope = rememberCoroutineScope()
    val unknown = stringResource(id = R.string.unknown)
    val text = rememberSaveable { mutableStateOf(unknown) }
    val isError = rememberSaveable { mutableStateOf(false) }
    textFiledModel.textFlow.onEach {
        text.value = it
    }.launchIn(scope)
    textFiledModel.isErrorFlow.onEach {
        isError.value = it == CreateLeadTextFiledState.ERROR
    }.launchIn(scope)

    val color = if (text.value == unknown || text.value == stringResource(
            id = textFiledModel.secondaryDefaultTextId
        )
    ) colorResource(id = R.color.label_text_color)
    else MaterialTheme.colorScheme.onSurface

    OutlinedTextField(
        modifier = modifier,
        visualTransformation =
        if (textFiledModel.isNumber) PhoneNumberVisualTransformation(textFiledModel.countryCode.ifBlank { Locale.getDefault().country })
        else VisualTransformation.None,
        keyboardOptions = if (textFiledModel.isNumber) KeyboardOptions(keyboardType = KeyboardType.Phone) else KeyboardOptions.Default,
        value = text.value,
        isError = isError.value,
        singleLine = true,
        shape = CircleShape.copy(all = CornerSize(16)),
        onValueChange = {
            textFiledModel.onValueChangeListener?.invoke(it)
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            disabledSupportingTextColor = color,
            containerColor = MaterialTheme.colorScheme.background,
            cursorColor = color,
            disabledLabelColor = Color.Blue,
            unfocusedBorderColor = color,
            unfocusedLabelColor = color,
            unfocusedTextColor = color
        ),
        label = {
            Text(
                text = stringResource(id = textFiledModel.labelTextId),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
            )
        }
    )
}


val String.color get() = Color(android.graphics.Color.parseColor(this))