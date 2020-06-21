package com.example.domain.base.usecase

import com.example.domain.base.executor.PostExecutionThread
import com.example.domain.base.executor.ThreadExecutor
import io.reactivex.Maybe
import javax.inject.Inject


/**
 * @author Aldyaz Nugroho on 01/06/20.
 * https://github.com/aldyaz
 */
abstract class RxMaybeUseCase<in Param, Result> @Inject constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    abstract fun getMaybe(param: Param): Maybe<Result>

    fun executeMaybe(param: Param): Maybe<Result> = getMaybe(param)
        .subscribeOn(threadExecutor.scheduler)
        .observeOn(postExecutionThread.scheduler)

}