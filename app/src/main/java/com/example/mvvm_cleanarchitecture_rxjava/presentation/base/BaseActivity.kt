package com.example.mvvm_cleanarchitecture_rxjava.presentation.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mvvm_cleanarchitecture_rxjava.presentation.HomeScreen
import com.example.mvvm_cleanarchitecture_rxjava.presentation.HomeToolbar
import com.example.mvvm_cleanarchitecture_rxjava.presentation.MainViewModel
import com.example.mvvm_cleanarchitecture_rxjava.ui.theme.AppTheme
import com.example.mvvm_cleanarchitecture_rxjava.ui.theme.dimens

abstract class BaseActivity<VIEW_MODEL : BaseViewModel> : ComponentActivity() {

    abstract val viewModel: VIEW_MODEL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                GetScreenView()
            }
        }
    }

    @Composable
    abstract fun GetScreenView()
}