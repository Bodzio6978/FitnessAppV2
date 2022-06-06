package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search.componens.SearchTopSection

@Composable
fun SearchScreen() {

    Column(
        modifier = Modifier
        .fillMaxSize()
    ) {
        SearchTopSection(
            mealName = "Breakfast",
            date = "Today",
            onClickEvent = {

            }
        )

    }
}