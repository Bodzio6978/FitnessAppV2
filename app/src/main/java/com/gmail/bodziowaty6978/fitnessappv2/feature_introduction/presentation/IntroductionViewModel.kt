package com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.navigation.Navigator
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.snackbar.SnackbarTextHolder
import com.gmail.bodziowaty6978.fitnessappv2.common.presentation.navigation.NavigationActions
import com.gmail.bodziowaty6978.fitnessappv2.common.util.CustomResult
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.model.Question
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.domain.use_cases.SaveIntroductionInformation
import com.gmail.bodziowaty6978.fitnessappv2.feature_introduction.presentation.util.IntroductionExpectedQuestionAnswer
import com.gmail.bodziowaty6978.fitnessappv2.util.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IntroductionViewModel @Inject constructor(
    resourceProvider: ResourceProvider,
    private val saveIntroductionInformation: SaveIntroductionInformation,
    private val navigator: Navigator,
    private val snackbarTextHolder: SnackbarTextHolder
): ViewModel(){

    private val _questionState = mutableStateOf<Map<IntroductionExpectedQuestionAnswer, Question>>(
        mapOf(
            IntroductionExpectedQuestionAnswer.Gender to Question(
                title = resourceProvider.getString(R.string.what_is_your_gender),
                tiles = resourceProvider.getStringArray(R.array.gender)
            ),
            IntroductionExpectedQuestionAnswer.Age to Question(
                title = resourceProvider.getString(R.string.what_is_your_age),
            ),
            IntroductionExpectedQuestionAnswer.Height to Question(
                title = resourceProvider.getString(R.string.what_is_your_height),
                unit = "cm",
            ),
            IntroductionExpectedQuestionAnswer.CurrentWeight to Question(
                title = resourceProvider.getString(R.string.what_is_your_current_weight),
                unit = "kg",
            ),
            IntroductionExpectedQuestionAnswer.TypeOfWork to Question(
                title = resourceProvider.getString(R.string.what_type_of_work_do_you_have),
                tiles = resourceProvider.getStringArray(R.array.works)
            ),
            IntroductionExpectedQuestionAnswer.HowOftenDoYouTrain to Question(
                title = resourceProvider.getString(R.string.how_often_do_you_train_in_a_week),
                tiles = resourceProvider.getStringArray(R.array.trainingsPerWeek),
            ),
            IntroductionExpectedQuestionAnswer.ActivityDuringADay to Question(
                title = resourceProvider.getString(R.string.how_would_you_describe_your_activity_level_during_a_day),
                tiles = resourceProvider.getStringArray(R.array.activity),
            ),
            IntroductionExpectedQuestionAnswer.DesiredWeight to Question(
                title = resourceProvider.getString(R.string.what_is_your_desired_weight),
                unit = "kg",
            )
        )
    )
    val questionState: State<Map<IntroductionExpectedQuestionAnswer, Question>> = _questionState
    
    private val _introductionUiEvent = MutableSharedFlow<IntroductionUiEvent>()
    val introductionUiEvent: SharedFlow<IntroductionUiEvent> = _introductionUiEvent

    private val _introductionAnswerState = mutableStateOf(
        mapOf(
            IntroductionExpectedQuestionAnswer.Gender to "0",
            IntroductionExpectedQuestionAnswer.Age to "18",
            IntroductionExpectedQuestionAnswer.Height to "180.0",
            IntroductionExpectedQuestionAnswer.CurrentWeight to "80.0",
            IntroductionExpectedQuestionAnswer.TypeOfWork to "0",
            IntroductionExpectedQuestionAnswer.HowOftenDoYouTrain to "0",
            IntroductionExpectedQuestionAnswer.TypeOfWork to "0",
            IntroductionExpectedQuestionAnswer.DesiredWeight to "80.0",
            IntroductionExpectedQuestionAnswer.ActivityDuringADay to "0"
        )
    )
    val introductionAnswerState: State<Map<IntroductionExpectedQuestionAnswer,String>> = _introductionAnswerState



    fun onEvent(event: IntroductionEvent){
        when(event){
            is IntroductionEvent.ClickedArrow -> {
                viewModelScope.launch {
                    if (event.isForward) {
                        _introductionUiEvent.emit(
                            IntroductionUiEvent.MoveForward
                        )
                    }else{
                        _introductionUiEvent.emit(
                            IntroductionUiEvent.MoveBackward
                        )
                    }
                }
            }
            is IntroductionEvent.EnteredAnswer -> {
                val currentAnswerState = _introductionAnswerState.value.toMutableMap()
                currentAnswerState[event.expectedQuestionAnswer] = event.answer
                _introductionAnswerState.value = currentAnswerState
            }
            is IntroductionEvent.FinishIntroduction -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val result = saveIntroductionInformation(answers = _introductionAnswerState.value)
                    Log.e(TAG,result.toString())
                    if (result is CustomResult.Error){
                        snackbarTextHolder.showSnackbarText(result.message)
                    }else{
                        navigator.navigate(NavigationActions.IntroductionScreen.introductionToSummary())
                    }
                }
            }
        }
    }



}