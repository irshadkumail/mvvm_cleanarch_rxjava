package com.example.mvvm_cleanarchitecture_rxjava.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class Album(
    @PrimaryKey
    val id: Int,
    val userId: Int,
    val title: String
)