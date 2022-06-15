package com.example.mvvm_cleanarchitecture_rxjava.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photo_table")
data class Photo(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val url: String,
    val title: String,
    val thumbnailUrl: String
)