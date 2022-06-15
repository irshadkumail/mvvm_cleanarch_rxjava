package com.example.mvvm_cleanarchitecture_rxjava.data.providers.local

import androidx.room.*
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import io.reactivex.Observable

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(list: List<Album>)

    @Query("SELECT * FROM album_table ORDER BY id")
    fun getAlbums(): Observable<List<Album>>

    @Delete
    fun deleteAlbum(album: Album)

    @Update
    fun updateAlbum(album: Album)
}