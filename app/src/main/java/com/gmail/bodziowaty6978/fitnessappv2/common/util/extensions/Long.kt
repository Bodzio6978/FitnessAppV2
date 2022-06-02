package com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions

import android.text.format.DateUtils
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.DateModel
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.util.DateType
import java.text.SimpleDateFormat
import java.util.*

fun Long.formatToAppDateModel(): DateModel {
    val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
    val currentDate = Date(this)
    val formattedDate = format.format(currentDate)

    if (DateUtils.isToday(this)){
        return DateModel(this,formattedDate,DateType.Today)
    }
    if(DateUtils.isToday(currentDate.time - DateUtils.DAY_IN_MILLIS)){
        return DateModel(this,formattedDate,DateType.Tomorrow)
    }

    if(DateUtils.isToday(currentDate.time + DateUtils.DAY_IN_MILLIS)){
        return DateModel(this,formattedDate,DateType.Yesterday)
    }

    return DateModel(this,formattedDate)
}