package com.example.mvvm_cleanarchitecture_rxjava.presentation

import com.example.mvvm_cleanarchitecture_rxjava.RxImmediateSchedulerRule
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.AlbumModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.PhotoModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.UserModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.GetAlbumsUseCase
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.GetPhotosUseCase
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.GetUsersUseCase
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainViewModelTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @MockK
    lateinit var usersUseCase: GetUsersUseCase

    @MockK
    lateinit var getPhotosUseCase: GetPhotosUseCase

    @MockK
    lateinit var albumsUseCase: GetAlbumsUseCase

    @Test
    fun testSuccess() {

        every { albumsUseCase.execute() }.returns(
            Observable.just(listOf(AlbumModel(id = 123, userId = 565, title = "Album Title")))
        )

        every { getPhotosUseCase.execute() }.returns(
            Observable.just(
                listOf(
                    PhotoModel(
                        id = 999,
                        albumId = 123,
                        title = "Photo Title",
                        thumbnailUrl = "Photo-thumbnail-url"
                    )
                )
            )
        )

        every { usersUseCase.execute() }.returns(
            Observable.just(
                listOf(
                    UserModel(
                        id = 565,
                        name = "User Name",
                        email = "user@gmail.com",
                        phone = "54321",
                        website = "test.com"
                    )
                )
            )
        )

        val mainViewModel = MainViewModel(albumsUseCase, getPhotosUseCase, usersUseCase)

        Assert.assertEquals(1, mainViewModel.uiList.size)
        Assert.assertNull(mainViewModel.handleError)

    }

    @Test
    fun testError() {

        every { albumsUseCase.execute() }.returns(
            Observable.just(listOf(AlbumModel(id = 123, userId = 565, title = "Album Title")))
        )

        every { getPhotosUseCase.execute() }.returns(
            Observable.error(Exception())
        )

        every { usersUseCase.execute() }.returns(
            Observable.just(
                listOf(
                    UserModel(
                        id = 565,
                        name = "User Name",
                        email = "user@gmail.com",
                        phone = "54321",
                        website = "test.com"
                    )
                )
            )
        )

        val mainViewModel = MainViewModel(albumsUseCase, getPhotosUseCase, usersUseCase)

        Assert.assertEquals(0, mainViewModel.uiList.size)
        Assert.assertNotNull(mainViewModel.handleError)

    }

}