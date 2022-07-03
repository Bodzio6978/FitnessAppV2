package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.componens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.DefaultTextField
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.TextFieldState
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.AquaBlue
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.search.SearchEvent

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
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "arrow back"
                )
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
                    color = AquaBlue
                )

            }

            Spacer(modifier = Modifier.width(24.dp))
        }

        Spacer(modifier = Modifier.height(5.dp))

        DefaultTextField(
            value = searchState.text,
            placeholder = {
                Text(text = searchState.hint)
            },
            modifier = Modifier
                .fillMaxWidth()
                .testTag(stringResource(id = R.string.SEARCH_TEXT_FIELD))
                .padding(horizontal = 20.dp),
            onValueChange = {
                onEvent(SearchEvent.EnteredSearchText(it))
            },
            shape = RoundedCornerShape(25)
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}