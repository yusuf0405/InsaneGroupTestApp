package com.joseph.insanegrouptestapp.presentation.lead_profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.common.CustomTextFiled
import com.joseph.insanegrouptestapp.presentation.common.LeadAppBar
import com.joseph.insanegrouptestapp.presentation.common.color
import com.joseph.insanegrouptestapp.presentation.models.ContactUiModel
import com.joseph.insanegrouptestapp.presentation.models.LeadDetailsUi
import com.joseph.insanegrouptestapp.presentation.models.TextFiledModelOrientation

@Composable
fun LeadProfileScreen(
    uiState: LeadProfileScreenState,
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
) {
    if (uiState.loading) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary
            )
        }
    }

    uiState.lead?.apply {
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            LeadAppBar(
                onNavigateBack = onNavigateBack,
                title = stringResource(id = R.string.lead_profile)
            )

            LazyVerticalGrid(
                modifier = modifier.fillMaxWidth(),
                columns = GridCells.Fixed(2),
            ) {
                item(span = { GridItemSpan(2) }) { LeadInformation(lead = this@apply) }
                val verticalTextFields = uiState.textFiledModels.filter {
                    it.orientation == TextFiledModelOrientation.VERTICAL
                }
                val horizontalTextFields = uiState.textFiledModels.filter {
                    it.orientation == TextFiledModelOrientation.HORIZONTAL
                }
                items(
                    count = verticalTextFields.size,
                ) { index ->
                    CustomTextFiled(
                        modifier = modifier.padding(all = 5.dp),
                        textFiledModel = verticalTextFields[index],
                    )
                }
                items(
                    count = horizontalTextFields.size,
                    span = { GridItemSpan(2) }
                ) { index ->
                    CustomTextFiled(
                        modifier = modifier.padding(all = 5.dp),
                        textFiledModel = horizontalTextFields[index],
                    )
                }
            }
        }
    }
}

@Composable
private fun LeadInformation(
    modifier: Modifier = Modifier,
    lead: LeadDetailsUi,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 12.dp),
    ) {
        Row(
            modifier = modifier
                .padding(horizontal = 10.dp)
        ) {
            AsyncImage(
                modifier = modifier
                    .clip(CircleShape)
                    .size(56.dp),
                model = lead.avatarUrl,
                contentDescription = null,
            )
            Column(
                modifier = modifier.padding(
                    start = 12.dp
                )
            ) {
                Text(
                    text = lead.displayName,
                    style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "ID: ${lead.id}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 15.sp),
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Icon(
                modifier = modifier.padding(
                    start = 16.dp,
                    top = 5.dp
                ),
                tint = colorResource(id = R.color.light_gray),
                painter = painterResource(id = R.drawable.ic_refactor),
                contentDescription = null
            )
        }
        Card(
            modifier = modifier
                .fillMaxWidth()
                .padding(
                    top = 12.dp,
                    start = 10.dp,
                    end = 10.dp
                )
                .height(100.dp),
            shape = RoundedCornerShape(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onSecondary
            ),
            border = BorderStroke(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
            ),
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 14.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
                        color = MaterialTheme.colorScheme.onSurface,
                        text = "${stringResource(id = R.string.lead_status)}:"
                    )
                    Spacer(modifier = modifier.weight(1f))
                    Canvas(
                        modifier = modifier
                            .padding(top = 5.dp)
                            .size(10.dp),
                        onDraw = {
                            drawCircle(color = lead.statusLegacyColor.color)
                        }
                    )
                    Text(
                        modifier = modifier.padding(start = 9.dp),
                        text = stringResource(id = R.string.options_sent),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp)
                    )
                    Icon(
                        modifier = modifier
                            .padding(
                                start = 17.dp,
                                top = 3.dp
                            ),
                        painter = painterResource(id = R.drawable.ic_arrow_right),
                        contentDescription = null,
                        tint = colorResource(id = R.color.light_gray)
                    )
                }
                val steps = 1..lead.statusStep
                LazyRow(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    contentPadding = PaddingValues(all = 4.dp)
                ) {
                    items(lead.statusStepCount) { step ->
                        StepItem(
                            modifier = modifier.weight(1f),
                            step = step,
                            steps = steps,
                            statusColor = lead.statusColor
                        )
                    }
                }
            }
        }
        LazyRow(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
        ) {
            items(lead.contactUiModels) { contact ->
                ContactItem(contact = contact)
            }
        }
        ProfileGeneralInfo()
    }
}

@Composable
private fun StepItem(
    modifier: Modifier = Modifier,
    steps: IntRange,
    step: Int,
    statusColor: String,
) {
    val color =
        if (steps.contains((step + 1))) statusColor.color
        else colorResource(id = R.color.gray)
    Card(
        shape = RoundedCornerShape(3.dp),
        modifier = modifier
            .width(42.dp)
            .height(9.dp)
            .padding(
                end = 4.dp,
            ),
        colors = CardDefaults.cardColors(
            containerColor = color
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 1.dp
        )
    ) {

    }
}

@Composable
private fun ContactItem(
    modifier: Modifier = Modifier,
    contact: ContactUiModel,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val alpha = if (contact.enabled) 0.5f else 0.15f
        Card(
            modifier = modifier
                .size(50.dp)
                .alpha(alpha),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.violet)
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = contact.iconId),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
        val textAlpha = if (contact.enabled) 1f else 0.15f
        Text(
            modifier = modifier
                .padding(top = 13.dp)
                .alpha(textAlpha),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
            text = stringResource(id = contact.titleId),
            color = colorResource(id = R.color.violet),
        )
    }
}

@Composable
private fun ProfileGeneralInfo(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 19.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.general_info),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Icon(
            modifier = modifier
                .padding(start = 8.dp),
            painter = painterResource(id = R.drawable.ic_refactor),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.outline,
        )
    }
}