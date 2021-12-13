package com.example.domain.base.usecase

import com.example.domain.base.executor.PostExecutionThread
import com.example.domain.base.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */
abstract class RxCompletableUseCase<in Param> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    abstract fun getCompletable(param: Param): Completable

    fun executeCompletable(param: Param): Completable = getCompletable(param)
        .subscribeOn(threadExecutor.scheduler)
        .observeOn(postExecutionThread.scheduler)

}