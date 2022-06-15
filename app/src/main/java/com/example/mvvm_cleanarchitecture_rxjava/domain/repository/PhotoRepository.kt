package com.example.mvvm_cleanarchitecture_rxjava.domain.repository

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import io.reactivex.Observable

interface PhotoRepository {

    fun getPhotos(): Observable<List<Photo>>

    fun getPhotosFromLocal(): Observable<List<Photo>>

    fun insertPhotos(list: List<Photo>)

}