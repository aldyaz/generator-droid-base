package com.example.domain.base.usecase

import com.example.domain.base.exception.Failure
import com.example.domain.base.executor.PostExecutionThread
import com.example.domain.base.executor.ThreadExecutor
import com.example.domain.base.functional.Either
import io.reactivex.Maybe
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableMaybeObserver
import javax.inject.Inject


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */
abstract class RxMaybeUseCase<in Param, Result> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    private val compositeDisposable by lazy { CompositeDisposable() }

    abstract fun createMaybe(param: Param): Maybe<Either<Failure, Result>>

    fun execute(param: Param, subscriber: DisposableMaybeObserver<Either<Failure, Result>>) =
        compose {
            createMaybe(param)
                .subscribeOn(threadExecutor.scheduler)
                .observeOn(postExecutionThread.scheduler)
                .subscribeWith(subscriber)
        }

    private fun compose(action: () -> Disposable) = compositeDisposable.add(action())

}