package com.example.mvvm_cleanarchitecture_rxjava.domain.usecase

import com.example.mvvm_cleanarchitecture_rxjava.RxImmediateSchedulerRule
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.AlbumRepository
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.PhotoRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class GetPhotosUseCaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun test_success() {
        val repository = mockk<PhotoRepository>()

        every { repository.getPhotosFromLocal() }.returns(
            Observable.just(
                listOf(
                    Photo(
                        1,
                        2,
                        url = "sample-url",
                        title = "test",
                        thumbnailUrl = "sample-url"
                    )
                )
            )
        )
        every { repository.getPhotos() }.returns(
            Observable.just(
                listOf(
                    Photo(
                        1,
                        2,
                        url = "sample-url",
                        title = "test",
                        thumbnailUrl = "sample-url"
                    )
                )
            )
        )

        every { repository.insertPhotos(any()) }.returns(Unit)

        val useCase = GetPhotosUseCase(repository)

        val observer = useCase.execute().test()

        Assert.assertEquals(1, observer.completions())
        Assert.assertEquals(1, observer.valueCount())
        Assert.assertEquals(0, observer.errors().size)
    }

    @Test
    fun test_failure() {
        val repository = mockk<PhotoRepository>()

        every { repository.getPhotosFromLocal() }.returns(
            Observable.just(
                listOf(
                    Photo(
                        1,
                        2,
                        url = "sample-url",
                        title = "test",
                        thumbnailUrl = "sample-url"
                    )
                )
            )
        )
        every { repository.getPhotos() }.returns(Observable.error(IOException()))
        every { repository.insertPhotos(any()) }.returns(Unit)

        val useCase = GetPhotosUseCase(repository)

        val observer = useCase.execute().test()

        Assert.assertEquals(1, observer.completions())
        Assert.assertEquals(1, observer.valueCount())
        Assert.assertEquals(0, observer.errors().size)
    }

}