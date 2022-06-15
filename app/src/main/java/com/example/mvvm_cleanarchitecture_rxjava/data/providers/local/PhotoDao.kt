package com.example.mvvm_cleanarchitecture_rxjava.data.providers.local

import androidx.room.*
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import io.reactivex.Observable


@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(list: List<Photo>)

    @Query("SELECT * FROM photo_table ORDER BY id")
    fun getPhotos(): Observable<List<Photo>>

    @Delete
    fun deletePhoto(photo: Photo)

    @Update
    fun updatePhoto(photo: Photo)
}