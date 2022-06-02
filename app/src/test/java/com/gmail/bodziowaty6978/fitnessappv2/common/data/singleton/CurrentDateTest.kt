package com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton

import android.text.format.DateUtils
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class CurrentDateTest{

    @Test
    fun `deduct date, correct date`(){
        val startingDate = CurrentDate.dateModel().timestamp
        CurrentDate.deductDay()
        val newDate = CurrentDate.dateModel().timestamp
        assertThat(newDate).isEqualTo(startingDate-DateUtils.DAY_IN_MILLIS)
    }

    @Test
    fun `add date, correct date`(){
        val startingDate = CurrentDate.dateModel().timestamp
        CurrentDate.addDay()
        val newDate = CurrentDate.dateModel().timestamp
        assertThat(newDate).isEqualTo(startingDate+DateUtils.DAY_IN_MILLIS)
    }
}