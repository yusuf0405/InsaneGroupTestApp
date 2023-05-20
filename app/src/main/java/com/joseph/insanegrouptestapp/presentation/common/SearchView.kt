package com.joseph.insanegrouptestapp.presentation.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.joseph.insanegrouptestapp.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CustomSearchView(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val color = colorResource(id = R.color.light_gray)
    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(all = 16.dp),
        value = searchQuery,
        textStyle = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.sp),
        singleLine = true,
        shape = CircleShape.copy(all = CornerSize(16)),
        onValueChange = { query ->
            searchQuery = query
            onSearch.invoke(query)
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
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null
            )
        }
    )
}