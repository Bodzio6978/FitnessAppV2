package com.gmail.bodziowaty6978.fitnessappv2.features.feature_auth.presentation.login.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.gmail.bodziowaty6978.fitnessappv2.R

@Composable
fun GoogleSignInButton(

) {

    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(CircleShape)
            .background(Color.LightGray)
            .padding(10.dp)

    ){
        Icon(painterResource(
            id = R.drawable.ic_google_g_logo),
            contentDescription = "Google icon",
        tint = Color.Black)
    }


}