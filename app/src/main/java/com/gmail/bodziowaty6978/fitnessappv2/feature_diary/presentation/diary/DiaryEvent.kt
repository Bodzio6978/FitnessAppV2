package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.presentation.diary

sealed class DiaryEvent {
    object ChangedDate:DiaryEvent()
    data class ClickedDiaryEntry(val diaryEntryId:String):DiaryUiEvent()
    data class ClickedAddProduct(val mealName:String):DiaryEvent()
}