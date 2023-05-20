package com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.models.LeadIntentionType


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLeadIntentionTypesBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    leadIntentionTypes: List<LeadIntentionType>,
    currentSelectedLeadIntentionType: LeadIntentionType,
    onSelectedLeadIntention: (LeadIntentionType) -> Unit
) {
    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Text(
            modifier = modifier
                .padding(
                    bottom = 8.dp,
                    start = 16.dp
                ),
            text = stringResource(id = R.string.select_type),
            style = MaterialTheme.typography.titleLarge
        )
        LazyVerticalGrid(
            modifier = modifier
                .padding(bottom = 16.dp),
            columns = GridCells.Fixed(2),
        ) {
            items(leadIntentionTypes.size) {
                SelectedColouredRadioButton(
                    leadIntentionType = leadIntentionTypes[it],
                    currentSelectedValue = currentSelectedLeadIntentionType.id,
                    onSelected = { lead ->
                        onSelectedLeadIntention(lead)
                    }
                )
            }
        }

    }
}

@Composable
fun SelectedColouredRadioButton(
    modifier: Modifier = Modifier,
    leadIntentionType: LeadIntentionType,
    onSelected: (LeadIntentionType) -> Unit,
    currentSelectedValue: Int
) {
    Row {
        RadioButton(
            selected = currentSelectedValue == leadIntentionType.id,
            onClick = { onSelected(leadIntentionType) },
            enabled = true,
            colors = RadioButtonDefaults.colors(selectedColor = colorResource(id = R.color.select_color))
        )
        Text(
            modifier = modifier
                .padding(top = 12.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            text = leadIntentionType.title,
        )
    }

}