package com.example.mvvm_cleanarchitecture_rxjava.domain.usecase

import com.example.mvvm_cleanarchitecture_rxjava.RxImmediateSchedulerRule
import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.AlbumRepository
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class GetUsersUseCaseTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun test_success() {
        val repository = mockk<UserRepository>()

        every { repository.getUsers() }.returns(
            Observable.just(
                listOf(
                    User(
                        id = 1,
                        name = "name",
                        username = "test",
                        email = "email@gmail.com",
                        phone = "1234",
                        website = "sample-website"
                    )
                )
            )
        )
        every { repository.getUsersFromLocal() }.returns(
            Observable.just(
                listOf(
                    User(
                        id = 1,
                        name = "name",
                        username = "test",
                        email = "email@gmail.com",
                        phone = "1234",
                        website = "sample-website"
                    )
                )
            )
        )
        every { repository.insertUsers(any()) }.returns(Unit)

        val useCase = GetUsersUseCase(repository)

        val observer = useCase.execute().test()

        Assert.assertEquals(1, observer.completions())
        Assert.assertEquals(1, observer.valueCount())
        Assert.assertEquals(0, observer.errors().size)
    }

    @Test
    fun test_failure() {
        val repository = mockk<UserRepository>()

        every { repository.getUsersFromLocal() }.returns(
            Observable.just(
                listOf(
                    User(
                        id = 1,
                        name = "name",
                        username = "test",
                        email = "email@gmail.com",
                        phone = "1234",
                        website = "sample-website"
                    )
                )
            )
        )
        every { repository.getUsers() }.returns(Observable.error(IOException()))
        every { repository.insertUsers(any()) }.returns(Unit)

        val useCase = GetUsersUseCase(repository)

        val observer = useCase.execute().test()

        Assert.assertEquals(1, observer.completions())
        Assert.assertEquals(1, observer.valueCount())
        Assert.assertEquals(0, observer.errors().size)
    }

}