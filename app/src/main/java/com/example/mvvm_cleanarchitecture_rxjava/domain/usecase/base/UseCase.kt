package com.example.mvvm_cleanarchitecture_rxjava.domain.usecase.base

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.IOException

/**
 * Base class for all usecase
 **/
abstract class UseCase<Result> {

    protected abstract fun buildObservable(): Observable<Result>

    fun execute(): Observable<Result> {
        return buildObservable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    }

}

