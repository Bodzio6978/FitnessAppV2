package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases.diary

import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.DiaryEntryWithId
import com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.model.Meal

class SortDiaryEntries(){

    operator fun invoke(
        entries:List<DiaryEntryWithId>,
        mealNames:List<String>
    ):List<Meal>{
        val meals = mutableListOf<Meal>()
        mealNames.forEach { mealName ->
            val entriesList = mutableListOf<DiaryEntryWithId>()
            entries.forEach { diaryEntry ->
                if (diaryEntry.diaryEntry.mealName==mealName){
                    entriesList.add(diaryEntry)
                }
            }
            meals.add(Meal(mealName=mealName, diaryEntries = entriesList))

        }
        return meals
    }
}