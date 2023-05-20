package com.joseph.insanegrouptestapp.presentation.leads_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.models.LeadModel
import kotlin.math.max

@Composable
fun LeadsScreen(
    uiState: LeadsScreenState,
    modifier: Modifier = Modifier,
    onNavigateCreateLeadScreen: () -> Unit,
    onNavigateLeadProfile: (Int) -> Unit,
) {
    if (uiState.loading) {
        Row(
            modifier = modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
    if (uiState.leads.isNotEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            CreateNewLead(
                onClickListener = onNavigateCreateLeadScreen
            )
            Divider(
                color = colorResource(id = R.color.driver_color),
                thickness = 1.dp
            )

            LazyColumn {
                items(uiState.leads.size) { position ->
                    LeadItem(
                        lead = uiState.leads[position],
                        onClickListener = onNavigateLeadProfile
                    )
                }
            }
        }
    }
}

@Composable
private fun CreateNewLead(
    modifier: Modifier = Modifier,
    onClickListener: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSecondary)
            .clickable { onClickListener() }
            .padding(all = 18.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_create_lead),
            contentDescription = null,
            tint = colorResource(id = R.color.text_secondary)
        )

        Text(
            modifier = modifier.padding(start = 14.dp),
            text = stringResource(id = R.string.create_new_lead),
            color = colorResource(id = R.color.text_secondary)
        )
    }
}

@Composable
private fun LeadItem(
    modifier: Modifier = Modifier,
    lead: LeadModel,
    onClickListener: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.onSecondary)
            .clickable { onClickListener(lead.id) }
            .padding(
                horizontal = 13.dp,
                vertical = 8.dp
            )
    ) {
        Row {
            AsyncImage(
                modifier = modifier
                    .clip(CircleShape)
                    .size(50.dp),
                model = lead.avatarUrl,
                contentDescription = null,
            )
            Column {
                Text(
                    modifier = modifier
                        .padding(start = 12.dp),
                    text = lead.displayNameAndEmoji(),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontSize = 16.sp
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                ChipFlowLayout(
                    listOf(
                        lead.leadStatus,
                        lead.channelSource,
                        lead.leadSources,
                        lead.leadIntentionType,
                        lead.channelSource,
                    )
                )
            }
        }

        Text(
            modifier = modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp),
            color = colorResource(id = R.color.text_secondary),
            text = "${stringResource(id = R.string.created_date)}: ${lead.getFormattedCreatedDate()}"
        )

        Text(
            modifier = modifier.padding(top = 4.dp),
            style = MaterialTheme.typography.titleMedium.copy(fontSize = 13.sp),
            color = colorResource(id = R.color.text_secondary),
            text = "${stringResource(id = R.string.updated_date)}: ${lead.getFormattedUpdatedDate()}"
        )
    }
    Divider(
        modifier = modifier,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        thickness = 1.dp
    )

}

@Composable
fun ChipFlowLayout(
    tags: Collection<String>
) {
    val linesSupport = remember {
        mutableStateOf(2)
    }
    Column(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
    ) {
        WarpFlowRow(
            linesSupport,
            verticalGap = 8.dp,
            horizontalGap = 8.dp,
            alignment = Alignment.Start,
            modifier = Modifier
                .padding(4.dp),

        ) {
            for (tag in tags) {
                if (tag.isNotBlank()) {
                    Text(
                        text = tag,
                        maxLines = 1,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 12.sp),
                        color = colorResource(id = R.color.text_secondary),
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .background(
                                colorResource(id = R.color.gray),
                                RoundedCornerShape(16.dp)
                            )
                            .padding(
                                vertical = 4.dp,
                                horizontal = 10.dp
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun WarpFlowRow(
    lineSupport: MutableState<Int>,
    modifier: Modifier = Modifier,
    alignment: Alignment.Horizontal = Alignment.Start,
    verticalGap: Dp = 0.dp,
    horizontalGap: Dp = 0.dp,
    content: @Composable () -> Unit,
) = Layout(content, modifier) { measurables, constraints ->
    val hGapPx = horizontalGap.roundToPx()
    val vGapPx = verticalGap.roundToPx()
    val rows = mutableListOf<MeasuredRow>()
    val itemConstraints = constraints.copy(minWidth = 0)
    var mCurrentLine = 0
    for (measurable in measurables) {
        val lastRow = rows.lastOrNull()
        val placeable = measurable.measure(itemConstraints)

        if (lastRow != null && lastRow.width + hGapPx + placeable.width <= constraints.maxWidth) {
            lastRow.items.add(placeable)
            lastRow.width += hGapPx + placeable.width
            lastRow.height = max(lastRow.height, placeable.height)
        } else {
            mCurrentLine++
            val nextRow = MeasuredRow(
                items = mutableListOf(placeable),
                width = placeable.width,
                height = placeable.height
            )
            if (mCurrentLine <= lineSupport.value) {
                rows.add(nextRow)
            }
        }
    }

    val width = rows.maxOfOrNull { row -> row.width } ?: 0
    val height = rows.sumBy { row -> row.height } + max(vGapPx.times(rows.size - 1), 0)

    val coercedWidth = width.coerceIn(constraints.minWidth, constraints.maxWidth)
    val coercedHeight = height.coerceIn(constraints.minHeight, constraints.maxHeight)

    layout(coercedWidth, coercedHeight) {
        var y = 0

        for (row in rows) {
            var x = when (alignment) {
                Alignment.Start -> 0
                Alignment.CenterHorizontally -> (coercedWidth - row.width) / 2
                Alignment.End -> coercedWidth - row.width
                else -> throw Exception("unsupported alignment")
            }
            for (item in row.items) {
                item.place(x, y)
                x += item.width + hGapPx
            }
            y += row.height + vGapPx
        }
    }
}

private data class MeasuredRow(
    val items: MutableList<Placeable>,
    var width: Int,
    var height: Int,
)