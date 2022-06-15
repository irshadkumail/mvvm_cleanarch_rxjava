package com.example.mvvm_cleanarchitecture_rxjava.domain.usecase

import com.example.mvvm_cleanarchitecture_rxjava.RxImmediateSchedulerRule
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.AlbumRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import io.reactivex.Observable
import okhttp3.ResponseBody
import okio.IOException
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception


class GetAlbumsUseCaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun test_success() {
        val repository = mockk<AlbumRepository>()

        every { repository.getAlbumsFromLocal() }.returns(
            Observable.just(
                listOf(
                    Album(
                        1,
                        2,
                        "test"
                    )
                )
            )
        )
        every { repository.getAlbums() }.returns(Observable.just(listOf(Album(1, 2, "test"))))
        every { repository.insertAll(any()) }.returns(Unit)

        val useCase = GetAlbumsUseCase(repository)

        val observer = useCase.execute().test()

        Assert.assertEquals(1, observer.completions())
        Assert.assertEquals(1, observer.valueCount())
        Assert.assertEquals(0, observer.errors().size)
    }

    @Test
    fun test_failure() {
        val repository = mockk<AlbumRepository>()

        every { repository.getAlbumsFromLocal() }.returns(
            Observable.just(
                listOf(
                    Album(
                        1,
                        2,
                        "test"
                    )
                )
            )
        )
        every { repository.getAlbums() }.returns(Observable.error(IOException()))
        every { repository.insertAll(any()) }.returns(Unit)

        val useCase = GetAlbumsUseCase(repository)

        val observer = useCase.execute().test()

        Assert.assertEquals(1, observer.completions())
        Assert.assertEquals(1, observer.valueCount())
        Assert.assertEquals(0, observer.errors().size)
    }

}