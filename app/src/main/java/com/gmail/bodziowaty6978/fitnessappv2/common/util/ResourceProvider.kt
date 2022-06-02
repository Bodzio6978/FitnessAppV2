package com.gmail.bodziowaty6978.fitnessappv2.common.util

import android.content.Context
import androidx.annotation.ArrayRes
import androidx.annotation.StringRes

class ResourceProvider(private val context: Context) {

    fun getString(@StringRes stringResId: Int): String {
        return context.getString(stringResId)
    }

    fun getStringArray(@ArrayRes stringArrayId:Int):List<String>{
        return context.resources.getStringArray(stringArrayId).toList()
    }
}