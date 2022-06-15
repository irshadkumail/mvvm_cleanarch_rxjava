package com.example.mvvm_cleanarchitecture_rxjava.data.providers

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.AlbumDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.PhotoDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.UserDao


@Database(entities = [Album::class, Photo::class, User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun albumDao(): AlbumDao

    abstract fun photoDao(): PhotoDao

    abstract fun userDao(): UserDao
}