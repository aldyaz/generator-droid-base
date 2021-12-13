package com.example.domain.base.executor

import io.reactivex.rxjava3.core.Scheduler


interface ThreadExecutor {
    val scheduler: Scheduler
}