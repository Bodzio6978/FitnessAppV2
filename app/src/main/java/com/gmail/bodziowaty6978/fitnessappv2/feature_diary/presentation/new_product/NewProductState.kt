package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.graphics.vector.ImageVector

data class NewProductState(
    val isDropDownMenuExpanded:Boolean = false,
    val dropDownMenuImageVector: ImageVector = Icons.Default.ArrowDropDown,
    val dropDownItems:List<String> = emptyList(),
    val dropDownSelectedIndex:Int = 0,
    val productName:String = "",
    val containerWeight:String,
    val containerWeightValue:String = "",
    val calories:String = "",
    val carbohydrates:String = "",
    val protein:String = "",
    val fat:String ="",
    val nutritionSelectedTabIndex:Int = 0,
    val barcode:String = ""
)
