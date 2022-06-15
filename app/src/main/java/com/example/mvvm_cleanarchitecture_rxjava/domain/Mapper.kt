package com.example.mvvm_cleanarchitecture_rxjava.domain

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.AlbumModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.PhotoModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.UserModel

fun Album.toDomainModel() = AlbumModel(
    id = this.id,
    userId = this.userId,
    title = this.title
)

fun Photo.toDomainModel() = PhotoModel(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    albumId = albumId
)

fun User.toDomainModel() = UserModel(
    id = id,
    name = name,
    email = email,
    phone = phone,
    website = website
)
