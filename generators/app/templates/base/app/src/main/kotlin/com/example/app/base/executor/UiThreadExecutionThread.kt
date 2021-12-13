package <%= appPackage %>.base.executor

import com.example.domain.base.executor.PostExecutionThread
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler


class UiThreadExecutionThread : PostExecutionThread {

    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()

}