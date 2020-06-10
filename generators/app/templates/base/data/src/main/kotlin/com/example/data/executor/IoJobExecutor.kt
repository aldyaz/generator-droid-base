package com.example.data.executor

import com.example.domain.base.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers


/**
 * @author Aldyaz Nugroho on 02/06/20.
 * https://github.com/aldyaz
 */
class IoJobExecutor : ThreadExecutor {
    override val scheduler: Scheduler
        get() = Schedulers.io()
}