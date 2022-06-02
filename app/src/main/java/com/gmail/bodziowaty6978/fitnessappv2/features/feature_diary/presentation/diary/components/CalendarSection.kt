package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton.CurrentDate
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.util.DateType

@Composable
fun CalendarSection(
    onArrowPressed: () -> Unit
) {
    val currentDateModel = CurrentDate.dateModel()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        IconButton(
            onClick = {
                CurrentDate.deductDay()
                onArrowPressed()
            },
            modifier = Modifier.testTag(stringResource(id = R.string.CALENDAR_BACK))
        ) {
            Icon(imageVector = Icons.Default.ArrowBackIos, contentDescription = "Back arrow")
        }


        Text(
            text = when (currentDateModel.dateType) {
                is DateType.Tomorrow -> stringResource(id = R.string.tomorrow)
                is DateType.Yesterday -> stringResource(id = R.string.yesterday)
                is DateType.Today -> stringResource(id = R.string.today)
                else -> currentDateModel.date
            },
            style = MaterialTheme.typography.h2,
            modifier = Modifier.testTag(stringResource(id = R.string.CALENDAR_DATE))
        )

        IconButton(
            onClick = {
                CurrentDate.addDay()
                onArrowPressed()
            },
            modifier = Modifier.testTag(stringResource(id = R.string.CALENDAR_FORWARD))
        ) {
            Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = "Back arrow")
        }


    }
}