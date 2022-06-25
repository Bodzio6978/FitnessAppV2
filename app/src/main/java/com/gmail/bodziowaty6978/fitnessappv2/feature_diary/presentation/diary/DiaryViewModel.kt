package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton.CurrentDate
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.navigation.navigator.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.util.Resource
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Meal
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.GetDiaryEntries
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val getDiaryEntries: GetDiaryEntries,
    private val resourceProvider: ResourceProvider,
    private val navigator: Navigator
): ViewModel(){

    private val _mealsState = mutableStateOf<List<Meal>>(
        listOf(
            Meal(mealName = resourceProvider.getString(R.string.breakfast), diaryEntries = emptyList()),
            Meal(mealName = resourceProvider.getString(R.string.lunch), diaryEntries = emptyList()),
            Meal(mealName = resourceProvider.getString(R.string.dinner), diaryEntries = emptyList()),
            Meal(mealName = resourceProvider.getString(R.string.supper), diaryEntries = emptyList()),
        )
    )
    val mealsState: State<List<Meal>> = _mealsState

    private val _diaryUiEvent = MutableSharedFlow<DiaryUiEvent>()
    val diaryUiEvent: SharedFlow<DiaryUiEvent> = _diaryUiEvent

    fun onEvent(event: DiaryEvent){
        when(event){
            is DiaryEvent.ChangedDate -> {
                getDiaryEntries()
            }
            is DiaryEvent.ClickedAddProduct -> {
                navigator.navigate(NavigationActions.DiaryScreen.diaryToSearch(event.mealName))
            }
        }
    }

    fun getDiaryEntries(){
        val currentDate = CurrentDate.dateModel(resourceProvider = resourceProvider).date

        viewModelScope.launch(Dispatchers.IO) {
            val resource = getDiaryEntries(
                date = currentDate,
                mealNames = resourceProvider.getStringArray(R.array.meal_names).toList()
            )
            Log.e(TAG,resource.toString())

            when(resource){
                is Resource.Success -> {
                    val data = resource.data!!
                    _mealsState.value = data
                    Log.e(TAG,data.toString())
                }
                is Resource.Error -> {
                    _diaryUiEvent.emit(DiaryUiEvent.ShowSnackbar(resource.uiText!!))
                }
            }
        }
    }
}
