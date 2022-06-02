package com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.presentation.diary

sealed class DiaryEvent {
    object ChangedDate:DiaryEvent()
    data class ClickedDiaryEntry(val diaryEntryId:String):DiaryUiEvent()
}