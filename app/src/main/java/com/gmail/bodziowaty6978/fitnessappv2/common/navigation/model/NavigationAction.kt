package com.gmail.bodziowaty6978.fitnessappv2.common.navigation.model

import android.os.Parcelable
import androidx.navigation.NavOptions

interface NavigationAction {
    val destination:String
    val parcelableArguments:Map<String,Parcelable>
        get() = emptyMap()
    val navOptions:NavOptions
        get() = NavOptions.Builder().build()
}