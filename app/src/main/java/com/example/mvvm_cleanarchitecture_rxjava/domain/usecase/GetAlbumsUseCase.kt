package com.example.mvvm_cleanarchitecture_rxjava.domain.usecase

import com.example.mvvm_cleanarchitecture_rxjava.data.models.Album
import com.example.mvvm_cleanarchitecture_rxjava.domain.models.AlbumModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.repository.AlbumRepository
import com.example.mvvm_cleanarchitecture_rxjava.domain.toDomainModel
import com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.base.UseCase
import io.reactivex.Observable
import org.reactivestreams.Subscriber
import javax.inject.Inject

class GetAlbumsUseCase
@Inject constructor(private val repository: AlbumRepository) :
    UseCase<List<AlbumModel>>() {

    override fun buildObservable(): Observable<List<AlbumModel>> {
        return repository.getAlbums()
            .doOnNext {
                repository.insertAll(it)
            }.onErrorResumeNext { throwable: Throwable ->
                repository.getAlbumsFromLocal()
            }.flatMap { Observable.just(mapper(it)) }
    }

    private fun mapper(input: List<Album>): List<AlbumModel> {
        return input.map { it.toDomainModel() }
    }

}