package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product

sealed class ProductEvent {
    object ClickedBackArrow:ProductEvent()
    object ClickedAddProduct:ProductEvent()
    data class EnteredWeight(val value:String, val product: Product):ProductEvent()
    data class ClickedWeightButton(val value:Int):ProductEvent()
}