package com.example.mvvm_cleanarchitecture_rxjava.domain.repository

import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import io.reactivex.Observable

interface UserRepository {

    fun getUsers(): Observable<List<User>>

    fun getUsersFromLocal(): Observable<List<User>>

    fun insertUsers(list: List<User>)
}