package com.gmail.bodziowaty6978.fitnessappv2.common.util

sealed class CustomResult {
    object Success: CustomResult()
    data class Error(val message:String): CustomResult()
}