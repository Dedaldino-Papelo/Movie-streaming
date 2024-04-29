package com.example.moviestreamingapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moviestreamingapp.R
import com.example.moviestreamingapp.model.Category
import com.example.moviestreamingapp.ui.components.MovieScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    categories: List<Category>,
    movieUiState: MovieUiState,
    modifier: Modifier = Modifier
) {
    var isSelected by rememberSaveable { mutableStateOf(false) }
    var categoryId by rememberSaveable { mutableStateOf("1") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background_color))
    ) {
        Spacer(modifier = modifier.size(24.dp))

        Text(
            text = stringResource(R.string.home_title),
            fontSize = 25.sp,
            color = colorResource(R.color.white),
            lineHeight = 43.sp,
            modifier = modifier
                .padding(start = 28.dp, end = 81.dp)
        )
        Spacer(modifier = modifier.size(24.dp))

        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(start = 28.dp, end = 28.dp),
            value = "",
            onValueChange = {},
            label = { Text(text = "Sherlock Holmes") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = ""
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = colorResource(R.color.textfield_color),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(50.dp)
        )
        Spacer(modifier = modifier.size(24.dp))

        Row(
            modifier = modifier
                .horizontalScroll(rememberScrollState())
                .fillMaxWidth(),
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                horizontalArrangement = Arrangement.spacedBy(28.dp),
            ) {
                categories.forEach { category ->
                    selectCategory(
                        category = category,
                        onSelect = {
                            isSelected = !isSelected
                            categoryId = category.id
                        },
                        selected = isSelected,
                        categoryId = categoryId
                    )
                }
            }
        }
        Spacer(modifier = modifier.size(24.dp))

        when(categoryId){
            "1" -> MovieScreen(movieUiState = movieUiState)
        }
    }
}

@Composable
fun selectCategory(
    category: Category,
    onSelect: () -> Unit,
    selected: Boolean,
    categoryId: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable { onSelect() }
    ) {
        Text(
            text = category.name,
            color = if (category.id == categoryId)
                colorResource(R.color.category_selected_color)
            else
                colorResource(R.color.white),
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 4.dp) // Adjust padding as needed
        )
        if (category.id == categoryId) {
            Box(
                modifier = Modifier
                    .width(28.dp)
                    .height(2.dp)
                    .background(
                        color = colorResource(R.color.category_selected_color),
                        RoundedCornerShape(50.dp)
                    )
            )
        }
    }
}