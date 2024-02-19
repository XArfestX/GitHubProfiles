package com.arfest.githubprofiles.core.design.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.arfest.githubprofiles.R

private val robotoFamily = FontFamily(
    Font(R.font.roboto_regular)
)


val typography = Typography(
    headlineMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 18.sp,
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W600
    ),
    labelMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 18.sp,
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W600
    ),
    bodyMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontFamily = robotoFamily,
        fontWeight = FontWeight.W400
    ),
)