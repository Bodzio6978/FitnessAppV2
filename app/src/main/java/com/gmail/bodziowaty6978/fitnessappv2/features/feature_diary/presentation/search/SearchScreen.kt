package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton.CurrentDate
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search.componens.SearchTopSection

@Composable
fun SearchScreen(
    mealName:String
) {

    Column(
        modifier = Modifier
        .fillMaxSize()
    ) {
        SearchTopSection(
            mealName = mealName,
            date = CurrentDate.dateModel(LocalContext.current).valueToDisplay ?: CurrentDate.dateModel(LocalContext.current).date,
            onClickEvent = {

            }
        )

    }
}