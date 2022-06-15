package com.example.mvvm_cleanarchitecture_rxjava.data.repository

import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.local.UserDao
import com.example.mvvm_cleanarchitecture_rxjava.data.providers.remote.ApiService
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.UserRepository
import io.reactivex.Observable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao
) : UserRepository {

    override fun getUsers(): Observable<List<User>> {
        return apiService.getUsers()
    }

    override fun getUsersFromLocal(): Observable<List<User>> {
        return userDao.getUsers()
    }

    override fun insertUsers(list: List<User>) {
        userDao.insertUsers(list)
    }


}