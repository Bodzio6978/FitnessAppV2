package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.ProductWithId

@Composable
fun ProductScreen(
    productWithId: ProductWithId
) {

    Text(text = productWithId.productId)

}