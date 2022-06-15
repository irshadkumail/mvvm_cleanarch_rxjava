package com.example.mvvm_cleanarchitecture_rxjava.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

class Dimens(
    //Padding values
    val paddingExtraExtraSmall: Dp = 4.dp,
    val paddingExtraSmall: Dp = 8.dp,
    val paddingSmall: Dp = 12.dp,
    val paddingMedium: Dp = 16.dp,
    val paddingLarge: Dp = 20.dp,
    val paddingExtraLarge: Dp = 24.dp,
)


val LocalDimens = staticCompositionLocalOf { Dimens() }

val MaterialTheme.dimens
    @Composable
    @ReadOnlyComposable
    get() = LocalDimens.current