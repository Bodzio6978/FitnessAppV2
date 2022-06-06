package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search.componens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton.CurrentDate
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Yellow
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search.SearchEvent

@Composable
fun SearchTopSection(
    mealName: String,
    date: String,
    onClickEvent:(SearchEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {
                onClickEvent(SearchEvent.ClickedBackArrow)
            }
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "arrow back")
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = mealName,
                style = MaterialTheme.typography.h2
            )

            Text(
                text = date,
                style = MaterialTheme.typography.body2,
                color = TextGrey
            )

        }

        Button(
            onClick = {
                onClickEvent(SearchEvent.ClickedSearch)
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Text(
                text = stringResource(id = R.string.search),
                color = Yellow,
                fontSize = 16.sp
            )
        }
    }
}