package com.example.mvvm_cleanarchitecture_rxjava.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

class TypographyLocal(
    val heading_2a: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
    ),
    val heading_2b: TextStyle = TextStyle(
        fontSize = 17.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    ),
    val heading_3a: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
    ),
    val heading_3b: TextStyle = TextStyle(
        fontSize = 15.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    ),
    val heading_4a: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
    ),
    val heading_4b: TextStyle = TextStyle(
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    ),
    val body_b: TextStyle = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.sp,
    ),
    val body_c: TextStyle = TextStyle(
        fontSize = 11.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.sp,
    )
)

val LocalTypography = staticCompositionLocalOf { TypographyLocal() }

val MaterialTheme.LocalTypo
    @Composable
    @ReadOnlyComposable
    get() = LocalTypography.current