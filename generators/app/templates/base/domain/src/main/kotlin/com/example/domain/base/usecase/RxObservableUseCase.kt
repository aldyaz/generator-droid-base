package com.example.domain.base.usecase

import com.example.domain.base.executor.PostExecutionThread
import com.example.domain.base.executor.ThreadExecutor
import io.reactivex.Observable
import javax.inject.Inject


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */
abstract class RxObservableUseCase<in Param, Result> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    abstract fun getObservable(param: Param): Observable<Result>

    fun executeObservable(param: Param): Observable<Result> = getObservable(param)
        .subscribeOn(threadExecutor.scheduler)
        .observeOn(postExecutionThread.scheduler)

}