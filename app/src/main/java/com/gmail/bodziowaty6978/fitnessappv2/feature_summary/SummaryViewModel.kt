package com.gmail.bodziowaty6978.fitnessappv2.feature_summary

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val instance : FirebaseAuth
): ViewModel(){

    fun logOutUser(){
        instance.signOut()
    }

}