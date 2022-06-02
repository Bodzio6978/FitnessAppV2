package com.gmail.bodziowaty6978.fitnessappv2.common.data.singleton

import android.text.format.DateUtils
import androidx.compose.runtime.mutableStateOf
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.DateModel
import com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions.formatToAppDateModel

object CurrentDate {
    private val date = mutableStateOf(
        System.currentTimeMillis()
    )

    fun dateModel():DateModel {
        return date.value.formatToAppDateModel()
    }

    fun addDay() {
        val newDate = date.value + DateUtils.DAY_IN_MILLIS
        date.value = newDate
    }

    fun deductDay() {
        val newDate = date.value - DateUtils.DAY_IN_MILLIS
        date.value = newDate
    }
}