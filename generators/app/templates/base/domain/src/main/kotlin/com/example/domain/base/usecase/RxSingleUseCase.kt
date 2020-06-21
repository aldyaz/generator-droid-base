package com.example.domain.base.usecase

import com.example.domain.base.executor.PostExecutionThread
import com.example.domain.base.executor.ThreadExecutor
import io.reactivex.Single
import javax.inject.Inject


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */
abstract class RxSingleUseCase<in Param, Result> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    abstract fun getSingle(param: Param): Single<Result>

    fun executeSingle(param: Param): Single<Result> = getSingle(param)
        .subscribeOn(threadExecutor.scheduler)
        .observeOn(postExecutionThread.scheduler)

}