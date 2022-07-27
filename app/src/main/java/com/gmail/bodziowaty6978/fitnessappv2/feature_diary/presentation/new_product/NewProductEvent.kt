package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product

sealed class NewProductEvent {
    object ClickedBackArrow:NewProductEvent()
    object ClickedDropDownMenu:NewProductEvent()
    object ClickedSaveButton:NewProductEvent()
    data class EnteredProductName(val value:String):NewProductEvent()
    data class ClickedDropDownMenuItem(val position:Int):NewProductEvent()
    data class ClickedNutritionTab(val position: Int):NewProductEvent()
    data class EnteredCalories(val value:String):NewProductEvent()
    data class EnteredCarbohydrates(val value:String):NewProductEvent()
    data class EnteredProtein(val value:String):NewProductEvent()
    data class EnteredFat(val value:String):NewProductEvent()
    data class EnteredContainerWeight(val value:String):NewProductEvent()
}