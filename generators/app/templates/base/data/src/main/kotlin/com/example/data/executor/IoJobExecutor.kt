package com.example.data.executor

import com.example.domain.base.executor.ThreadExecutor
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers


class IoJobExecutor : ThreadExecutor {

    override val scheduler: Scheduler
        get() = Schedulers.io()

}