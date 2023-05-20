package com.joseph.insanegrouptestapp.presentation.create_lead_screen.dialogs

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
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
import com.joseph.insanegrouptestapp.presentation.models.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectCityBottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    cities: List<City>,
    onSelectedCity: (City) -> Unit
) {
    ModalBottomSheet(
        modifier = modifier
            .height(600.dp),
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
            text = stringResource(id = R.string.select_city),
            style = MaterialTheme.typography.titleLarge
        )
        LazyColumn {
            items(cities.size) { position ->
                CityItem(
                    city = cities[position],
                    onClickListener = onSelectedCity
                )
            }
        }

    }
}

@Composable
private fun CityItem(
    modifier: Modifier = Modifier,
    city: City,
    onClickListener: (City) -> Unit
) {
    Column(
        modifier = modifier
            .padding(
                vertical = 12.dp,
                horizontal = 20.dp
            )
            .clickable {
                onClickListener(city)
            }
    ) {
        Text(
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 18.sp),
            text = city.title
        )
        Text(
            modifier = modifier.padding(top = 4.dp),
            text = city.timezone
        )
    }

}