package com.gmail.bodziowaty6978.fitnessappv2.common.util.extensions

import android.text.format.DateUtils
import com.gmail.bodziowaty6978.fitnessappv2.R
import com.gmail.bodziowaty6978.fitnessappv2.common.domain.model.DateModel
import com.gmail.bodziowaty6978.fitnessappv2.common.util.ResourceProvider
import java.util.*

fun Long.formatToAppDateModel(resourceProvider: ResourceProvider): DateModel {
    val currentDate = Date(this)
    val formattedDate = currentDate.formatToString()

    if (DateUtils.isToday(this)){
        return DateModel(this,formattedDate,resourceProvider.getString(R.string.today))
    }
    if(DateUtils.isToday(currentDate.time - DateUtils.DAY_IN_MILLIS)){
        return DateModel(this,formattedDate,resourceProvider.getString(R.string.tomorrow))
    }

    if(DateUtils.isToday(currentDate.time + DateUtils.DAY_IN_MILLIS)){
        return DateModel(this,formattedDate,resourceProvider.getString(R.string.yesterday))
    }

    return DateModel(this,formattedDate)
}