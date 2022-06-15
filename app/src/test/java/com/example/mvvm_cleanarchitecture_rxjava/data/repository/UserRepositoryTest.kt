package com.example.mvvm_cleanarchitecture_rxjava.data.repository

import com.example.mvvm_cleanarchitecture_rxjava.RxImmediateSchedulerRule
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.AlbumDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.UserDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote.ApiService
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserRepositoryTest {

    @Rule
    @JvmField
    var testSchedulerRule = RxImmediateSchedulerRule()

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @MockK
    lateinit var apiService: ApiService

    @MockK
    lateinit var userDao: UserDao

    @Test
    fun getUsers_Test() {
        val userRepository = UserRepositoryImpl(apiService, userDao)

        every { apiService.getUsers() }.returns(Observable.empty())

        userRepository.getUsers()

        verify(exactly = 1) { apiService.getUsers() }
    }

    @Test
    fun getAlbumsFromLocal_Test() {
        val userRepository = UserRepositoryImpl(apiService, userDao)

        every { userDao.getUsers() }.returns(Observable.empty())

        userRepository.getUsersFromLocal()

        verify(exactly = 1) { userDao.getUsers() }
    }

    @Test
    fun insertAll_Test() {
        val userRepository = UserRepositoryImpl(apiService, userDao)

        every { userDao.insertUsers(any()) }.returns(Unit)

        userRepository.insertUsers(emptyList())

        verify(exactly = 1) { userDao.insertUsers(any()) }
    }
}