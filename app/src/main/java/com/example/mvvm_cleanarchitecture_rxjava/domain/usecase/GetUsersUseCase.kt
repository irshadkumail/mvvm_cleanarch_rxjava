package com.example.mvvm_cleanarchitecture_rxjava.domain.usecase

import com.example.mvvm_cleanarchitecture_rxjava.data.models.User
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.UserModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.UserRepository
import com.example.mvvm_cleanarchitecture_rxjava.domain.toDomainModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.base.UseCase
import io.reactivex.Observable
import java.io.IOException
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(private val repository: UserRepository) :
    UseCase< List<UserModel>>() {

    override fun buildObservable(): Observable<List<UserModel>> {
        return repository.getUsers()
            .doOnNext {
                repository.insertUsers(it)
            }.onErrorResumeNext { throwable: Throwable ->
                repository.getUsersFromLocal()
            }.flatMap { Observable.just(mapper(it)) }
    }

    private fun mapper(input: List<User>): List<UserModel> {
        return input.map { it.toDomainModel() }
    }
}