package com.example.mvvm_cleanarchitecture_rxjava.domain.usecase

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Photo
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.PhotoModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.PhotoRepository
import com.example.mvvm_cleanarchitecture_rxjava.domain.toDomainModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.base.UseCase
import io.reactivex.Observable
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository: PhotoRepository) :
    UseCase<List<PhotoModel>>() {

    override fun buildObservable(): Observable<List<PhotoModel>> {
        return repository.getPhotos()
            .doOnNext {
                repository.insertPhotos(it)
            }.onErrorResumeNext { throwable: Throwable ->
                repository.getPhotosFromLocal()
            }.flatMap { Observable.just(mapper(it)) }

    }

    private fun mapper(input: List<Photo>): List<PhotoModel> {
        return input.map { it.toDomainModel() }
    }
}