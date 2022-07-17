package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.components.DefaultTextField
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.LightGrey
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.NewProductEvent
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product.NewProductState

@Composable
fun ContainerWeightSection(
    containerWeightText: String,
    state: NewProductState,
    onEvent: (NewProductEvent) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = containerWeightText,
            style = MaterialTheme.typography.body1
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            DefaultTextField(
                value = state.containerWeightValue,
                onValueChange = {
                    onEvent(NewProductEvent.EnteredContainerWeight(it))
                },
                modifier = Modifier
                    .width((LocalConfiguration.current.screenWidthDp * 0.25).dp)
                    .testTag(stringResource(id = R.string.container_weight) + "TEXT_FIELD"),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )

            Row(
                modifier = Modifier
                    .border(1.dp, LightGrey)
                    .clip(RoundedCornerShape(15))
                    .border(1.dp, LightGrey)
                    .clickable {
                        onEvent(NewProductEvent.ClickedDropDownMenu)
                    }
                    .border(1.dp, LightGrey)
                    .testTag(stringResource(id = R.string.container_weight) + "DROPDOWN_MENU"),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = state.dropDownItems[state.dropDownSelectedIndex],
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier
                        .padding(horizontal = 15.dp, vertical = 15.dp)

                )
                Icon(
                    imageVector = state.dropDownMenuImageVector,
                    contentDescription = "DropDown",
                    modifier = Modifier
                        .padding(end = 10.dp)
                )

                DropdownMenu(
                    expanded = state.isDropDownMenuExpanded,
                    onDismissRequest = {
                        onEvent(NewProductEvent.ClickedDropDownMenu)
                    }
                ) {
                    state.dropDownItems.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            onClick = {
                                onEvent(NewProductEvent.ClickedDropDownMenuItem(index))
                            },
                            modifier = Modifier
                                .testTag(item+"DROPDOWN_MENU_ITEM")
                        ) {
                            Text(
                                text = item,
                                style = MaterialTheme.typography.body1
                            )
                        }
                    }
                }
            }
        }
    }
}