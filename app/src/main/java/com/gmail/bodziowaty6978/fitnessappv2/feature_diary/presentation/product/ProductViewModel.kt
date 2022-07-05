package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Product
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.product.ProductUseCases
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.product.components.NutritionData
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val navigator: Navigator,
    private val productUseCases: ProductUseCases
) : ViewModel() {

    private val _weightState = mutableStateOf("100.0")
    val weightState: State<String> = _weightState

    private val _nutritionDataState = mutableStateOf(NutritionData())
    val nutritionDataState: State<NutritionData> = _nutritionDataState

    fun onEvent(event: ProductEvent) {
        when (event) {
            is ProductEvent.EnteredWeight -> {
                val enteredValue = event.value.toDoubleOrNull()
                enteredValue?.let {
                    viewModelScope.launch(Dispatchers.Default) {
                        _weightState.value = event.value
                        _nutritionDataState.value = nutritionDataState.value.copy(
                            nutritionValues = productUseCases.calculateNutritionValues(
                                weight = enteredValue,
                                product = event.product
                            )
                        )
                    }

                }
            }
            is ProductEvent.ClickedAddProduct -> {

            }
            is ProductEvent.ClickedBackArrow -> {
                navigator.navigate(NavigationActions.General.navigateUp())
            }
        }
    }

    fun initializeNutritionData(product: Product) {
        _nutritionDataState.value = NutritionData(
            nutritionValues = product.nutritionValues,
            pieEntries = productUseCases.createPieChartData(product = product)
        )
        Log.e(TAG,_nutritionDataState.value.toString())
    }
}