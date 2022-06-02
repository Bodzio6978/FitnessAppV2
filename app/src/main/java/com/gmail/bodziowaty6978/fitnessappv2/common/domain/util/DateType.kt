package com.gmail.bodziowaty6978.fitnessappv2.common.domain.util

sealed class DateType{
    object Random:DateType()
    object Tomorrow:DateType()
    object Today:DateType()
    object Yesterday:DateType()
}
