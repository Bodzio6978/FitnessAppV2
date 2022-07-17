package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.new_product

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.new_product.SaveNewProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewProductViewModel @Inject constructor(
    private val navigator: Navigator,
    private val resourceProvider: ResourceProvider,
    private val saveNewProduct: SaveNewProduct
) : ViewModel() {

    private val _state = MutableStateFlow(
        NewProductState(
            containerWeight = resourceProvider.getString(R.string.container_weight),
            dropDownItems = resourceProvider.getStringArray(R.array.units)
        )
    )
    val state: StateFlow<NewProductState> = _state

    fun onEvent(event: NewProductEvent) {
        when (event) {
            is NewProductEvent.ClickedDropDownMenu -> {
                _state.update {
                    it.copy(
                        isDropDownMenuExpanded = !it.isDropDownMenuExpanded
                    )
                }
                _state.update {
                    it.copy(
                        dropDownMenuImageVector = if (_state.value.isDropDownMenuExpanded) {
                            Icons.Default.ArrowDropUp
                        } else {
                            Icons.Default.ArrowDropDown
                        }
                    )
                }
            }
            is NewProductEvent.ClickedBackArrow -> {
                navigator.navigate(NavigationActions.General.navigateUp())
            }
            is NewProductEvent.EnteredProductName -> {
                _state.update {
                    it.copy(
                        productName = event.value
                    )
                }
            }
            is NewProductEvent.ClickedDropDownMenuItem -> {
                _state.update {
                    it.copy(
                        dropDownSelectedIndex = event.position,
                        isDropDownMenuExpanded = false
                    )
                }
            }
            is NewProductEvent.ClickedNutritionTab -> {
                _state.update {
                    it.copy(
                        nutritionSelectedTabIndex = event.position
                    )
                }
                when (event.position) {
                    0 -> {
                        _state.update {
                            it.copy(
                                containerWeight = resourceProvider.getString(R.string.container_weight)
                            )
                        }
                    }

                    1 -> {
                        _state.update {
                            it.copy(
                                containerWeight = resourceProvider.getString(R.string.container_weight) + "*"
                            )
                        }
                    }

                    2 -> {
                        _state.update {
                            it.copy(
                                containerWeight = resourceProvider.getString(R.string.average_weight) + "*"
                            )
                        }
                    }
                }
            }
            is NewProductEvent.EnteredCalories -> {
                val enteredValue = event.value.replace(",", "").replace(".", "")

                _state.update {
                    it.copy(
                        calories = enteredValue
                    )
                }
            }
            is NewProductEvent.EnteredCarbohydrates -> {
                val enteredValue = event.value.replace(",", ".")

                _state.update {
                    it.copy(
                        carbohydrates = enteredValue
                    )
                }
            }

            is NewProductEvent.EnteredProtein -> {
                val enteredValue = event.value.replace(",", ".")

                _state.update {
                    it.copy(
                        protein = enteredValue
                    )
                }
            }

            is NewProductEvent.EnteredFat -> {
                val enteredValue = event.value.replace(",", ".")

                _state.update {
                    it.copy(
                        fat = enteredValue
                    )
                }
            }

            is NewProductEvent.EnteredContainerWeight -> {
                val enteredValue = event.value.replace(",",".")

                _state.update {
                    it.copy(
                        containerWeightValue = enteredValue
                    )
                }
            }

            is NewProductEvent.ClickedSaveButton -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val state = _state.value
                    saveNewProduct(
                        name = state.productName,
                        containerWeight = state.containerWeight,
                        position = state.nutritionSelectedTabIndex,
                        unit = state.dropDownItems[state.dropDownSelectedIndex],
                        barcode = state.barcode,
                        calories = state.calories,
                        carbohydrates = state.carbohydrates,
                        protein = state.protein,
                        fat = state.fat,
                    )
                }
            }
        }
    }

}