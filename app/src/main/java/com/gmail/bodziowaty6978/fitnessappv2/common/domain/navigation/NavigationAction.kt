package com.gmail.bodziowaty6978.fitnessappv2.common.domain.navigation

import android.os.Parcelable
import androidx.navigation.NavOptions

interface NavigationAction {
    val destination:String
    val parcelableArguments:Map<String,Parcelable>
        get() = emptyMap<String,Parcelable>()
    val navOptions:NavOptions
        get() = NavOptions.Builder().build()
}