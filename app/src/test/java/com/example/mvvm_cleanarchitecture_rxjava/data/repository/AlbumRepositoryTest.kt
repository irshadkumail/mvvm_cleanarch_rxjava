package com.example.mvvm_cleanarchitecture_rxjava.data.repository

import com.example.mvvm_cleanarchitecture_rxjava.RxImmediateSchedulerRule
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.AlbumDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote.ApiService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumRepositoryTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @MockK
    lateinit var apiService: ApiService

    @MockK
    lateinit var albumDao: AlbumDao

    @Test
    fun getAlbums_Test() {
        val albumRepository = AlbumRepositoryImpl(apiService, albumDao)

        every { apiService.getAlbums() }.returns(Observable.empty())

        albumRepository.getAlbums()

        verify(exactly = 1) { apiService.getAlbums() }
    }

    @Test
    fun getAlbumsFromLocal_Test() {
        val albumRepository = AlbumRepositoryImpl(apiService, albumDao)

        every { albumDao.getAlbums() }.returns(Observable.empty())

        albumRepository.getAlbumsFromLocal()

        verify(exactly = 1) { albumDao.getAlbums() }
    }

    @Test
    fun insertAll_Test() {
        val albumRepository = AlbumRepositoryImpl(apiService, albumDao)

        every { albumDao.insertAlbum(any()) }.returns(Unit)

        albumRepository.insertAll(emptyList())

        verify(exactly = 1) { albumDao.insertAlbum(any()) }
    }
}