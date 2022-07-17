package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.BlueViolet3
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextGrey
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.ui.theme.TextWhite
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.ProductEvent

@Composable
fun ProductNameSection(
    product: Product,
    onEvent:(ProductEvent) -> Unit,
    currentWeight:String
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp),
        elevation = 3.dp,
        shape = RoundedCornerShape(15)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column {

                    Text(
                        text = stringResource(id = R.string.product_name),
                        style = MaterialTheme.typography.h3
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row {
                        Text(
                            text = product.name,
                            style = MaterialTheme.typography.body2.copy(
                                color = TextWhite
                            ),
                        )
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Card(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(25)
                    ) {
                        BasicTextField(
                            value = currentWeight,
                            onValueChange = {
                                onEvent(ProductEvent.EnteredWeight(it, product))
                            },
                            modifier = Modifier
                                .width(80.dp)
                                .padding(horizontal = 8.dp, vertical = 10.dp)
                                .testTag(stringResource(id = R.string.WEIGHT_TEXT_FIELD)),
                            textStyle = MaterialTheme.typography.body1.copy(
                                color = TextWhite,
                                textAlign = TextAlign.End
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            ),
                            cursorBrush = SolidColor(MaterialTheme.colors.primary)
                        )
                    }


                    Spacer(modifier = Modifier.width(4.dp))

                    Text(
                        text = stringResource(id = R.string.g),
                        style = MaterialTheme.typography.body2.copy(
                            color = TextGrey
                        )
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 13.dp)
                    .padding(bottom = 15.dp)
            ) {

                for(i in 50..200 step 50){
                    OutlinedButton(
                        onClick = {
                            onEvent(ProductEvent.EnteredWeight(value = i.toString(), product = product))
                        },
                        border = BorderStroke(1.dp, BlueViolet3),
                        shape = RoundedCornerShape(25),
                        modifier = Modifier
                            .testTag(i.toString() + stringResource(id = R.string.WEIGHT_BUTTON))
                    ) {
                        Text(
                            text = "${i}g",
                            color = BlueViolet3,
                            style = MaterialTheme.typography.button
                        )
                    }
                }
            }
        }
    }
}