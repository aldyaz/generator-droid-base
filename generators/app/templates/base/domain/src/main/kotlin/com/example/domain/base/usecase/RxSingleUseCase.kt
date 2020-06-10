package com.example.domain.base.usecase

import com.example.domain.base.exception.Failure
import com.example.domain.base.executor.PostExecutionThread
import com.example.domain.base.executor.ThreadExecutor
import com.example.domain.base.functional.Either
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */
abstract class RxSingleUseCase<in Param, Result> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val compositeDisposable by lazy { CompositeDisposable() }

    abstract fun createSingle(param: Param): Single<Either<Failure, Result>>

    fun execute(param: Param, subscriber: DisposableSingleObserver<Either<Failure, Result>>) =
        compose {
            createSingle(param)
                .subscribeOn(threadExecutor.scheduler)
                .observeOn(postExecutionThread.scheduler)
                .subscribeWith(subscriber)
        }

    private fun compose(action: () -> Disposable) = compositeDisposable.add(action())

}