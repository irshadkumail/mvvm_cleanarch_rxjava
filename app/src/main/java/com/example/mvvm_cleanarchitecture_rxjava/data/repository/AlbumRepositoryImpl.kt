package com.example.mvvm_cleanarchitecture_rxjava.data.repository

import android.util.Log
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.AlbumDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote.ApiService
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.AlbumRepository
import io.reactivex.Observable
import javax.inject.Inject


class AlbumRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val albumDao: AlbumDao
) : AlbumRepository {

    override fun getAlbums(): Observable<List<Album>> {
        return apiService.getAlbums()
    }

    override fun getAlbumsFromLocal(): Observable<List<Album>> {
        return  albumDao.getAlbums()
    }

    override fun insertAll(list: List<Album>) {
        albumDao.insertAlbum(list)
    }

}