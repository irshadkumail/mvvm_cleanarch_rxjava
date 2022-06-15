package com.example.mvvm_cleanarchitecture_rxjava.presentation.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    var handleError by mutableStateOf<String?>(null)
    var loadingState by mutableStateOf(false)
}