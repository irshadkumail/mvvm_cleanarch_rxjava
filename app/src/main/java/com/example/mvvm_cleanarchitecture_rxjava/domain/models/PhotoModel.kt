package com.example.mvvm_cleanarchitecture_rxjava.domain.models

data class PhotoModel(
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val albumId: Int
)