package com.example.mvvm_cleanarchitecture_rxjava.data.repository

import com.example.mvvm_cleanarchitecture_rxjava.RxImmediateSchedulerRule
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.AlbumDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.PhotoDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote.ApiService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PhotoRepositoryTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @MockK
    lateinit var apiService: ApiService

    @MockK
    lateinit var photoDao: PhotoDao

    @Test
    fun getAlbums_Test() {
        val photoRepository = PhotoRepositoryImpl(apiService, photoDao)

        every { apiService.getPhotos() }.returns(Observable.empty())

        photoRepository.getPhotos()

        verify(exactly = 1) { apiService.getPhotos() }
    }

    @Test
    fun getAlbumsFromLocal_Test() {
        val photoRepository = PhotoRepositoryImpl(apiService, photoDao)

        every { photoDao.getPhotos() }.returns(Observable.empty())

        photoRepository.getPhotosFromLocal()

        verify(exactly = 1) { photoDao.getPhotos() }
    }

    @Test
    fun insertAll_Test() {
        val photoRepository = PhotoRepositoryImpl(apiService, photoDao)

        every { photoDao.insertPhoto(any()) }.returns(Unit)

        photoRepository.insertPhotos(emptyList())

        verify(exactly = 1) { photoDao.insertPhoto(any()) }
    }

}