package com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.common.CustomSearchView
import com.joseph.insanegrouptestapp.presentation.models.Language
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectLanguageBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    languages: List<Language>,
    onSelectedLanguage: (Language) -> Unit,
    selectedLanguage: Language,
    onSearch: (String) -> Unit,
) {
    ModalBottomSheet(
        sheetState = rememberModalBottomSheetState().apply {
            rememberCoroutineScope().launch {
                delay(100)
                expand()
            }
        },
        modifier = modifier
            .height(600.dp),
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Text(
            modifier = modifier.padding(
                bottom = 8.dp,
                start = 16.dp
            ),
            text = stringResource(id = R.string.select_language),
            style = MaterialTheme.typography.titleLarge
        )
        CustomSearchView(onSearch = onSearch)
        LazyColumn {
            items(languages.size) { position ->
                LanguageItem(
                    language = languages[position],
                    onClickListener = onSelectedLanguage,
                    selectedLanguage = selectedLanguage
                )
            }
        }

    }
}

@Composable
private fun LanguageItem(
    modifier: Modifier = Modifier,
    language: Language,
    onClickListener: (Language) -> Unit,
    selectedLanguage: Language
) {

    Column(
        modifier = modifier
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            )
            .clickable {
                onClickListener(language)
            }
    ) {

        Row {
            Column {
                Text(
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
                    text = language.title
                )
                Text(
                    modifier = modifier,
                    style = MaterialTheme.typography.bodySmall,
                    text = language.shortCode
                )
            }
            Spacer(modifier = modifier.weight(1f))
            if (selectedLanguage == language) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_selected),
                    contentDescription = null
                )
            }
        }

        Divider(
            modifier = modifier
                .padding(top = 4.dp),
            color = colorResource(id = R.color.light_gray),
            thickness = 1.dp
        )
    }
}