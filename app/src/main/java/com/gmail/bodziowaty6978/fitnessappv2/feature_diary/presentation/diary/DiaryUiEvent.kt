package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary

sealed class DiaryUiEvent {
    data class ClickedDiaryEntry(val diaryEntryId:String):DiaryUiEvent()
    data class ShowSnackbar(val message:String):DiaryUiEvent()
}