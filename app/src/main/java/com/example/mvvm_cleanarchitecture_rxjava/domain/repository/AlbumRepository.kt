package com.example.mvvm_cleanarchitecture_rxjava.domain.repository

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import io.reactivex.Observable

interface AlbumRepository {

    fun getAlbums(): Observable<List<Album>>

    fun getAlbumsFromLocal(): Observable<List<Album>>

    fun insertAll(list: List<Album>)

}