package com.gmail.bodziowaty6978.fitnessappv2.feature_diary.domain.use_cases

import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntry
import com.gmail.bodziowaty6978.fitnessappv2.features.feature_diary.domain.model.DiaryEntryWithId
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
internal class SortDiaryEntriesTest{

    private lateinit var entries:MutableList<DiaryEntryWithId>
    private lateinit var sortDiaryEntries: SortDiaryEntries
    private lateinit var resourceProvider: ResourceProvider

    @Before
    fun setUp(){
        entries = mutableListOf()
        resourceProvider = ResourceProvider(RuntimeEnvironment.getApplication())
        sortDiaryEntries = SortDiaryEntries()
        val meals = resourceProvider.getStringArray(R.array.meal_names)
        for (i in 0..100){
            entries.add(DiaryEntryWithId("$i", DiaryEntry(mealName = meals.random())))
        }

    }

    @Test
    fun `sort Entries, correct size of returned list`(){
        println(entries.toString())
        val result = sortDiaryEntries(
            entries,
            resourceProvider.getStringArray(R.array.meal_names)
        )
        assertThat(result.sumOf { it.diaryEntries.size }).isEqualTo(entries.size)
    }

    @Test
    fun `sort Entries, correctly sorted entries`(){
        println(entries.toString())
        val result = sortDiaryEntries(
            entries,
            resourceProvider.getStringArray(R.array.meal_names)
        )
        result.forEach { meal ->
            meal.diaryEntries.forEach { diaryEntry ->
                assertThat(diaryEntry.diaryEntry.mealName).isEqualTo(meal.mealName)
            }
        }
    }

}