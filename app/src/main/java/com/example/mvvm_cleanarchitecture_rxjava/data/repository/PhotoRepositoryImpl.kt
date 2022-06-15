package com.example.mvvm_cleanarchitecture_rxjava.data.repository

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.PhotoDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.UserDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote.ApiService
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.PhotoRepository
import io.reactivex.Observable
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val photoDao: PhotoDao
) : PhotoRepository {
    override fun getPhotos(): Observable<List<Photo>> {
        return apiService.getPhotos()
    }

    override fun getPhotosFromLocal(): Observable<List<Photo>> {
        return photoDao.getPhotos()
    }

    override fun insertPhotos(list: List<Photo>) {
        photoDao.insertPhoto(list)
    }


}