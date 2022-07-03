package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

sealed class ProductEvent {
    object ClickedBackArrow:ProductEvent()
    object ClickedAddProduct:ProductEvent()
    data class EnteredWeight(val value:String):ProductEvent()
    data class ClickedWeightButton(val value:Int):ProductEvent()
}