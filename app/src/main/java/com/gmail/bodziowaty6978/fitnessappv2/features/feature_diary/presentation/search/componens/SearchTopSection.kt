package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search.componens

import com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.util.TextField
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.TextFieldState
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.Yellow
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.search.SearchEvent

@Composable
fun SearchTopSection(
    searchState:TextFieldState,
    mealName: String,
    date: String,
    onEvent: (SearchEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
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
                    onEvent(SearchEvent.ClickedBackArrow)
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

            Text(
                text = ""
            )
        }

        TextField(
            value = searchState.text,
            hint = searchState.hint,
            isHintVisible = searchState.isHintVisible,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(id = R.string.TEXT_FIELD)),
            textStyle = MaterialTheme.typography.body2,
            onValueChange = {
                onEvent(SearchEvent.EnteredSearchText(it))
            }
        )



    }
}