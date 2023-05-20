package com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joseph.insanegrouptestapp.R
import com.joseph.insanegrouptestapp.presentation.common.CustomSearchView
import com.joseph.insanegrouptestapp.presentation.models.Country
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.material3.Text as Text1

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCountriesBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    countries: List<Country>,
    onSelectedCountry: (Country) -> Unit,
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
        Text1(
            modifier = modifier
                .padding(
                    bottom = 8.dp,
                    start = 16.dp
                ),
            text = stringResource(id = R.string.select_country),
            style = MaterialTheme.typography.titleLarge
        )
        CustomSearchView(onSearch = onSearch)
        LazyColumn {
            items(countries.size) { position ->
                CountryItem(
                    country = countries[position],
                    onClickListener = onSelectedCountry
                )
            }
        }

    }
}


@Composable
fun CountryItem(
    modifier: Modifier = Modifier,
    country: Country,
    onClickListener: (Country) -> Unit
) {
    Row(
        modifier = modifier
            .padding(
                vertical = 8.dp,
                horizontal = 20.dp
            )
            .clickable {
                onClickListener(country)
            }
    ) {
        Text1(
            modifier = modifier.padding(end = 8.dp),
            text = country.emoji
        )
        Text1(
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 16.sp),
            text = country.title
        )
        Spacer(modifier = modifier.weight(1f))
        Text1(text = country.shortCode1)
    }

}