package com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("/photos")
    fun getPhotos(): Observable<List<Photo>>

    @GET("/albums")
    fun getAlbums(): Observable<List<Album>>

    @GET("/users")
    fun getUsers(): Observable<List<User>>
}