package com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.models.LeadSources

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLeadSourcesBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    leadSources: List<LeadSources>,
    onSelectedLeadSources: (LeadSources) -> Unit
) {
    ModalBottomSheet(
        modifier = modifier,
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
            text = stringResource(id = R.string.select_source),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn {
            items(leadSources.size) { position ->
                LeadSourcesItem(
                    leadSources = leadSources[position],
                    onClickListener = onSelectedLeadSources
                )
            }
        }

    }
}

@Composable
private fun LeadSourcesItem(
    modifier: Modifier = Modifier,
    leadSources: LeadSources,
    onClickListener: (LeadSources) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            )
            .clickable {
                onClickListener(leadSources)
            }
    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
            text = leadSources.title
        )
    }

}