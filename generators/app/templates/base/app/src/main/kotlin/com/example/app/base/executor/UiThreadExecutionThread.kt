package <%= appPackage %>.base.executor

import com.example.domain.base.executor.PostExecutionThread
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers


/**
 * @author Aldyaz Nugroho on 02/06/20.
 * https://github.com/aldyaz
 */

class UiThreadExecutionThread : PostExecutionThread {
    override val scheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}