package com.gmail.bodziowaty6978.fitnessappv2.common.util

sealed class Result {
    object Success: Result()
    data class Error(val message:String): Result()
}